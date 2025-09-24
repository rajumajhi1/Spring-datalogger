package com.datalogger.faults.config;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

@Component
public class ConnectionPoolHealthIndicator implements HealthIndicator {

    @Autowired
    private DynamicDataSourceConfig dataSourceConfig;

    @Override
    public Health health() {
        try {
            Map<String, DataSource> dataSources = dataSourceConfig.dataSources();
            Health.Builder builder = Health.up();
            
            for (Map.Entry<String, DataSource> entry : dataSources.entrySet()) {
                String key = entry.getKey();
                DataSource dataSource = entry.getValue();
                
                if (dataSource instanceof HikariDataSource) {
                    @SuppressWarnings("resource")
                    HikariDataSource hikariDS = (HikariDataSource) dataSource;
                    HikariPoolMXBean poolBean = hikariDS.getHikariPoolMXBean();
                    
                    if (poolBean != null) {
                        int activeConnections = poolBean.getActiveConnections();
                        int idleConnections = poolBean.getIdleConnections();
                        int totalConnections = poolBean.getTotalConnections();
                        int threadsAwaitingConnection = poolBean.getThreadsAwaitingConnection();
                        
                        builder.withDetail(key + "_active", activeConnections)
                               .withDetail(key + "_idle", idleConnections)
                               .withDetail(key + "_total", totalConnections)
                               .withDetail(key + "_waiting", threadsAwaitingConnection);
                        
                        // Check if pool is under stress
                        if (threadsAwaitingConnection > 0 || activeConnections >= totalConnections * 0.9) {
                            builder.down().withDetail(key + "_status", "Pool under stress");
                        }
                    }
                }
            }
            
            return builder.build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}