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
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value="com.hyundai.project.user.repository", sqlSessionFactoryRef="userSqlSessionFactory")
@EnableTransactionManagement
public class UserDatabaseConfig {
	
	@Primary
	@Bean(name = "userDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.user")
	public DataSource userDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name = "userSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("userDataSource") DataSource userDataSource, ApplicationContext applicationContext) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(userDataSource);
		sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mapper/user/*.xml"));
		return sessionFactory.getObject();
	}
	
	@Primary
	@Bean(name = "userSqlSession")
	public SqlSessionTemplate userSqlSession(SqlSessionFactory userSqlSessionFactory) {
		
		return new SqlSessionTemplate(userSqlSessionFactory);
	}
}