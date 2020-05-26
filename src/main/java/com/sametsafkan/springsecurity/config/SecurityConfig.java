package com.sametsafkan.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
		.withUser(users.username("samet").password("123456").roles("ADMIN", "EMPLOYEE"))
		.withUser(users.username("mehmet").password("123456").roles("ADMIN", "MANAGER", "EMPLOYEE"))
		.withUser(users.username("ahmet").password("123456").roles("EMPLOYEE"));*/
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/").hasRole("EMPLOYEE")
				.antMatchers("/system/**").hasRole("ADMIN")
				.antMatchers("/management/**").hasRole("MANAGER")
			.and()
			.formLogin()
				.loginPage("/showLogin")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout()
				.permitAll()
			.and()
			.exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
	
	
}
