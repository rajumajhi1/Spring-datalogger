package com.datalogger.faults.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DynamicDataSourceConfig {

    @Autowired
    private Environment environment;

    @Bean
    public Integer dataSourceCount() {
        String countStr = environment.getProperty("app.datasource.count");
        return Integer.parseInt(countStr != null ? countStr : "2"); // Default to 2 if not specified
    }

    @Bean
    @Primary
    public Map<String, DataSource> dataSources() {
        Map<String, DataSource> dataSources = new HashMap<>();
        
        int count = dataSourceCount();
        for (int i = 0; i < count; i++) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(environment.getProperty("spring.datasource.user[" + i + "].url"));
            config.setDriverClassName(environment.getProperty("spring.datasource.user[" + i + "].driver-class-name"));
            config.setUsername(environment.getProperty("spring.datasource.user[" + i + "].username"));
            config.setPassword(environment.getProperty("spring.datasource.user[" + i + "].password"));
            
            // Set connection timeout properties
            String connectionTimeout = environment.getProperty("spring.datasource.user[" + i + "].connection-timeout");
            String socketTimeout = environment.getProperty("spring.datasource.user[" + i + "].socket-timeout");
            
            // Apply HikariCP settings
            config.setConnectionTimeout(Long.parseLong(connectionTimeout != null ? connectionTimeout : "60000"));
            config.setValidationTimeout(5000);
            config.setMinimumIdle(2);
            config.setMaximumPoolSize(5);
            config.setIdleTimeout(300000);
            config.setMaxLifetime(1800000);
            config.setConnectionTestQuery("SELECT 1 FROM DUAL");
            
            // Add Oracle-specific properties
            if (socketTimeout != null) {
                config.addDataSourceProperty("oracle.net.READ_TIMEOUT", socketTimeout);
                config.addDataSourceProperty("oracle.net.CONNECT_TIMEOUT", socketTimeout);
            }
            
            HikariDataSource dataSource = new HikariDataSource(config);
            dataSources.put("dataSource" + i, dataSource);
        }
        
        return dataSources;
    }

    @Bean
    public Map<String, LocalContainerEntityManagerFactoryBean> entityManagerFactories() {
        Map<String, LocalContainerEntityManagerFactoryBean> factories = new HashMap<>();
        
        Map<String, DataSource> dataSources = dataSources();
        for (String key : dataSources.keySet()) {
            LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
            bean.setDataSource(dataSources.get(key));
            
            HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
            bean.setJpaVendorAdapter(adapter);
            
            HashMap<String, String> props = new HashMap<>();
            props.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
            props.put("hibernate.hbm2ddl.auto", "none");
            bean.setJpaPropertyMap(props);
            
            bean.setPackagesToScan("com.datalogger.faults.model");
            
            // Extract the index from the key (e.g., "dataSource2" -> "2")
            String index = key.substring(10);
            factories.put("entityManagerFactory" + index, bean);
            
            // Initialize the factory
            bean.afterPropertiesSet();
        }
        
        return factories;
    }

    @Bean
    public Map<String, PlatformTransactionManager> transactionManagers() {
        Map<String, PlatformTransactionManager> managers = new HashMap<>();
        
        Map<String, LocalContainerEntityManagerFactoryBean> factories = entityManagerFactories();
        for (String key : factories.keySet()) {
            // Extract the index from the key (e.g., "entityManagerFactory2" -> "2")
            String index = key.substring(18);
            
            LocalContainerEntityManagerFactoryBean factory = factories.get(key);
            if (factory == null || factory.getObject() == null) {
                throw new IllegalStateException("EntityManagerFactory not properly initialized for key: " + key);
            }
            
            JpaTransactionManager manager = new JpaTransactionManager();
            manager.setEntityManagerFactory(factory.getObject());
            manager.afterPropertiesSet();
            
            managers.put("transactionManager" + index, manager);
        }
        
        return managers;
    }
    
    // We don't create EntityManager beans directly as they are not thread-safe
    // Instead, we'll use EntityManagerFactory in the repository implementation
}