package com.gnet.integration.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JDBCTemplateConfig {

    @Primary
    @Bean(name = "prodJdbcTemplate")
    public JdbcTemplate prodJdbcTemplate(@Qualifier("prodDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "replicationJdbcTemplate")
    public JdbcTemplate replicationJdbcTemplate(@Qualifier("replicationDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}