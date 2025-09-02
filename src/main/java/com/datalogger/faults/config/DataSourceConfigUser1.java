/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 *  org.springframework.core.env.Environment
 *  org.springframework.data.jpa.repository.config.EnableJpaRepositories
 *  org.springframework.jdbc.datasource.DriverManagerDataSource
 *  org.springframework.orm.jpa.JpaTransactionManager
 *  org.springframework.orm.jpa.JpaVendorAdapter
 *  org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
 *  org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
 *  org.springframework.transaction.PlatformTransactionManager
 *  org.springframework.transaction.annotation.EnableTransactionManagement
 */
package com.datalogger.faults.config;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactoryBean", basePackages={"com.datalogger.faults.repo"}, transactionManagerRef="transactionManager1")
public class DataSourceConfigUser1 {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(this.environment.getProperty("spring.datasource.user1.url"));
        dataSource.setDriverClassName(this.environment.getProperty("spring.datasource.user1.driver-class-name"));
        dataSource.setUsername(this.environment.getProperty("spring.datasource.user1.username"));
        dataSource.setPassword(this.environment.getProperty("spring.datasource.user1.password"));
        return dataSource;
    }

    @Bean(name={"entityManagerFactoryBean"})
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(this.dataSource());
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter((JpaVendorAdapter)adapter);
        HashMap<String, String> props = new HashMap<String, String>();
        props.put("spring.jpa.user1.properties.hibernate.dialect", "org.hibernate.dialect.Oracle21cDialect");
        props.put("hibernate.hbm2ddl.auto", "none");
        bean.setJpaPropertyMap(props);
        bean.setPackagesToScan(new String[]{"com.datalogger.faults.model"});
        return bean;
    }

    @Bean(name={"transactionManager1"})
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(this.entityManagerFactoryBean().getObject());
        return manager;
    }
}
