package com.jon.attachat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AttaChatSercurityConfig extends WebSecurityConfigurerAdapter  {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Add our users for in memory authentication
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("test123").roles("USER"))
			.withUser(users.username("mary").password("test123").roles("USER"))
			.withUser(users.username("susan").password("test123").roles("USER"));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/Comment/userAction/**").hasRole("USER")
			.antMatchers("/Thread/userAction/**").hasRole("USER")
			.antMatchers("/Sub/userAction/**").hasRole("USER")
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout()
			.logoutSuccessUrl("/")
			.permitAll();	

	}
	
}
