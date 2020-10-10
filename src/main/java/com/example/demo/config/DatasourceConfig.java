package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DatasourceConfig {
	
	
	private String username;
	private String password;
	private String url;
	private String driver;
	
	
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getUrl() {
		return url;
	}
	public String getDriver() {
		return driver;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	@Bean(name = "dataSource")
	public DataSource datasource()
	{
		
		HikariDataSource ds=new HikariDataSource();
		ds.setDataSourceClassName(this.getDriver());
		ds.setJdbcUrl(this.getUrl());
		ds.setPassword(this.getPassword());
		ds.setUsername(this.getUsername());
		ds.setAutoCommit(false);
		return ds;
		
	}

	@Bean(name="transactionManager")
	public DataSourceTransactionManager datasourceTransactionManager()
	{
		return new DataSourceTransactionManager(datasource());
		
	}
	
	
	
	@Bean(name="jdbcTemplate")
	public JdbcTemplate jdbcTemplate()
	{
		return new JdbcTemplate(datasource());
		
	}
}
