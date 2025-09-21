package com.datalogger.faults.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicRepositoryFactory {

    @Autowired
    private ApplicationContext context;
    
    @Bean
    public Map<String, FaultRepository> faultRepositories() {
        Map<String, FaultRepository> repositories = new HashMap<>();
        
        Map<String, DataSource> dataSources = context.getBean("dataSources", Map.class);
        AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
        
        for (String key : dataSources.keySet()) {
            int index = Integer.parseInt(key.substring(10)); // Extract index from "dataSource0", "dataSource1", etc.
            
            // Create a repository for each datasource
            FaultRepositoryImpl repository = new FaultRepositoryImpl(index);
            beanFactory.autowireBean(repository); // Autowire the repository
            repositories.put("faultRepository" + index, repository);
        }
        
        return repositories;
    }
}