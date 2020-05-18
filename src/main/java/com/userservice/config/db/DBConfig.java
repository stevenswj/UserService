package com.userservice.config.db;

import java.util.Properties;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class DBConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driverClassName"));
        dataSource.setUrl(env.getProperty("url"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty(
                "hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty(
                "hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty(
                "hibernate.jdbc.batch_size", env.getProperty("hibernate.jdbc.batch_size"));
        sessionFactory.setPackagesToScan(new String[] {"com.userservice"});
        sessionFactory.setHibernateProperties(hibernateProperties);
        sessionFactory.setDataSource(dataSource());

        return sessionFactory;
    }
}