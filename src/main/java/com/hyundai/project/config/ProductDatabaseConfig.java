package com.hyundai.project.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value="com.hyundai.project.product.repository", sqlSessionFactoryRef="productSqlSessionFactory")
@EnableTransactionManagement
public class ProductDatabaseConfig {
	
	@Bean(name = "productDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.product")
	public DataSource productDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "productSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("productDataSource") DataSource productDataSource, ApplicationContext applicationContext) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(productDataSource);
		sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mapper/product/*.xml"));
		return sessionFactory.getObject();
	}
	
	@Bean(name = "productSqlSession")
	public SqlSessionTemplate productSqlTemplate(SqlSessionFactory productSqlSessionFactory) {
		
		return new SqlSessionTemplate(productSqlSessionFactory);
	}
}