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
@MapperScan(value="com.hyundai.project.board.repository", sqlSessionFactoryRef="boardSqlSessionFactory")
@EnableTransactionManagement
public class BoardDatabaseConfig {
	@Bean(name = "boardDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.board")
	public DataSource boardDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "boardSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("boardDataSource") DataSource boardDataSource, ApplicationContext applicationContext) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(boardDataSource);
		sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mapper/board/*.xml"));
		return sessionFactory.getObject();
	}
	
	@Bean(name = "boardSqlSession")
	public SqlSessionTemplate boardSqlTemplate(SqlSessionFactory boardSqlSessionFactory) {
		
		return new SqlSessionTemplate(boardSqlSessionFactory);
	}

}
