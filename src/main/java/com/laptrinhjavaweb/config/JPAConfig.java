package com.laptrinhjavaweb.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
//Để sử dụng được những phương thức của JpaRepository 
@EnableJpaRepositories(basePackages = {"com.laptrinhjavaweb.repository"})
//Bật tính năng quản lý Transaction
@EnableTransactionManagement	
public class JPAConfig {
	// Khởi tạo Entity Manager Factory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        // Open Connection 
        em.setDataSource(dataSource());
        // Là cầu nối giữa NewEntity và bảng new trong mysql có thể matching với nhau . Được khai báo trong META-INF
        em.setPersistenceUnitName("persistence-data");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        // Nơi chứa tất cả cấu hình của JPA . Vd Bật tính năng tự động tạo table từ Entity
        em.setJpaProperties(additionalProperties());
        return em;
    }
    
    // Bật tính năng tự động quản lý Transaction (Entity Transaction)
    @Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
    
    @Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	

    @Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/springmvcbasicfree?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		return dataSource;
	}
   
    Properties additionalProperties() {
    	Properties properties = new Properties();
    	// ở thời điểm muốn tạo table thì sẽ để mở dòng này . Tạo database từ những javaclass Entity 	
//    	properties.setProperty("hibernate.hbm2ddl.auto", "update");
//    	properties.setProperty("hibernate.hbm2ddl.auto", "create");
		properties.setProperty("hibernate.hbm2ddl.auto", "none");
    	properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
    	return properties;
    }
}
