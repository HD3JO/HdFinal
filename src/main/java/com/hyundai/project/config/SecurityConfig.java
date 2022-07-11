package com.hyundai.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/member/**").hasAnyRole("USER","ADMIN")
			//.antMatchers("/member/**").permitAll()
			.anyRequest().permitAll();
		
		http.formLogin()
			.loginPage("/customLogin").loginProcessingUrl("/login")
			.defaultSuccessUrl("/main")
			.failureHandler(customAuthenticationFailureHandler)
			.successHandler(customAuthenticationSuccessHandler);
		//http.formLogin();
		http.oauth2Login().defaultSuccessUrl("/main");
		
		//스마트에디터를 사용하기 위함
		http.headers().frameOptions().disable();

		//http.csrf().disable();	
	}
   
   
}

