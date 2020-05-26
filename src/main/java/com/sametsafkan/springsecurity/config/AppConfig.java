package com.sametsafkan.springsecurity.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.sametsafkan.springsecurity")
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig {
	
	@Autowired
	private Environment env;

	@Bean
	public ViewResolver getViewResulver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public DataSource securityDataSource() {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass(env.getProperty("jdbc.driver"));
			ds.setJdbcUrl(env.getProperty("jdbc.url"));
			ds.setUser(env.getProperty("jdbc.user"));
			ds.setPassword(env.getProperty("jdbc.password"));
			ds.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
			ds.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
			ds.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
			ds.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
			return ds;
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
	}
}
