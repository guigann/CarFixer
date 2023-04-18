package com.twoguis.carfixer.config;


import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Slf4JSqlLogger;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbiConfig {
    
    @Bean
    @ConfigurationProperties("spring.datasource")
    DataSource driverManagerDataSource() {
        return new DriverManagerDataSource();
    }
    
    @Bean
    Jdbi jdbi(DataSource dataSource) {
        return Jdbi.create(dataSource)
                .installPlugin(new SqlObjectPlugin())
                .setSqlLogger(new Slf4JSqlLogger());
    }
}
