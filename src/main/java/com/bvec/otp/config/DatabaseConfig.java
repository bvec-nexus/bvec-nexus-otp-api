package com.bvec.otp.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    @Autowired
    private DatabaseConfigProperties databaseConfigProperties;

    @Bean
    public DataSource dataSource(){

        logger.info("DatabaseConfig !! Entry");

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(databaseConfigProperties.getDriverClassName());
        dataSourceBuilder.url(databaseConfigProperties.getUrl());
        dataSourceBuilder.username(databaseConfigProperties.getUserName());
        dataSourceBuilder.password(databaseConfigProperties.getPassword());

        logger.info("DatabaseConfig !! Exit");

        return dataSourceBuilder.build();
    }
}
