package com.joongang.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan("com.joongang.repository") // 매퍼를 찾아 스프링빈으로 등록
@PropertySource(value = "classpath:database/db.properties")
@EnableTransactionManagement // 트랜잭션 설정
@EnableScheduling
public class RootConfig {
	
	@Value("${db.driver}")
	private String driverClassName;
	
	@Value("${db.url}")
	private String jdbcUrl; 
	
	@Value("${db.username}")
	private String username;
	
	@Value("${db.password}")
	private String password;
	
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driverClassName);
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(password);
		return new HikariDataSource(config);
	}
	
	@Bean
	public SqlSessionFactoryBean sessionFactoryBean() throws IOException {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:mappers/**/*Mapper.xml"));
//		factory.setTypeAliases(BoardVO.class);
		factory.setTypeAliasesPackage("com.joongang.domain");
		return factory;
	}
	
	@Bean
	public SqlSessionTemplate sessionTemplate() throws Exception {
		return new SqlSessionTemplate(sessionFactoryBean().getObject());
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource(); 
		messageSource.setBasenames("message.label");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer(); 
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
