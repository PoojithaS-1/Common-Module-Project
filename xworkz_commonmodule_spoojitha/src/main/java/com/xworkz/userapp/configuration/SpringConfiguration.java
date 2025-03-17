package com.xworkz.userapp.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableWebMvc
@Slf4j
@Configuration
@ComponentScan("com.xworkz.userapp")
public class SpringConfiguration {
    public SpringConfiguration() {

//        System.out.println("SpringConfiguration obj created");
        log.info("SpringConfiguration obj created");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(getDataSource());
        bean.setPackagesToScan("com.xworkz.userapp.entity");
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());//imp
        bean.setJpaProperties(getJpaProperties());
        return bean;

    }

    //@Bean //not required its object is not manages by spring container
    public Properties getJpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/project1");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("p00jitha@S");
        return driverManagerDataSource;
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
