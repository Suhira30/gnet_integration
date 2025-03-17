package com.gnet.integration.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import java.sql.SQLOutput;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.primary.url}")
    private String primaryPosgreUrl;

    @Value("${spring.datasource.primary.username}")
    private String primaryPosgreUsername;

    @Value("${spring.datasource.primary.password}")
    private String primaryPostgrepassword;

    @Value("${spring.datasource.secondary.url}")
    private String secondaryPosgreUrl;

    @Value("${spring.datasource.secondary.username}")
    private String secondaryPosgreUsername;

    @Value("${spring.datasource.secondary.password}")
    private String secondaryPostgrepassword;


    @Value("${spring.datasource.driver-class-name}")
    private String postgredriver;

    @Bean
    public DataSource prodDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(postgredriver);
        dataSource.setUrl(primaryPosgreUrl);
        dataSource.setUsername(primaryPosgreUsername);
        dataSource.setPassword(primaryPostgrepassword);
        System.out.println(".......................prodDataSource........>");
        return dataSource;
    }

    @Bean
    public DataSource replicationDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(postgredriver);
        dataSource.setUrl(secondaryPosgreUrl);
        dataSource.setUsername(secondaryPosgreUsername);
        dataSource.setPassword(secondaryPostgrepassword);
        return dataSource;
    }

}
