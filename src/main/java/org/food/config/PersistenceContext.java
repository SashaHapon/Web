package org.food.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.EntityManager;
import org.food.utils.PropertyUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PersistenceContext {
    PropertyUtils propertyUtils = new PropertyUtils("application.property");

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(propertyUtils.getProperty("driver"));
//        dataSource.setUrl(propertyUtils.getProperty("url"));
//        dataSource.setUsername(propertyUtils.getProperty("username"));
//        dataSource.setPassword(propertyUtils.getProperty("password"));
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
        dataSource.setUsername("root");
        dataSource.setPassword("12345");
        return dataSource;
    }

    @Bean
    public EntityManager entityManagerCreator(){
        return SharedEntityManagerCreator.createSharedEntityManager(entityManagerFactory().getObject());
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }



    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter  = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        em.setEntityManagerInterface(hibernateJpaVendorAdapter.getEntityManagerInterface());
        em.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        em.setJpaProperties(additionalProperties());
        em.setPackagesToScan("org.food.model");
        return em;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return properties;
    }

}
