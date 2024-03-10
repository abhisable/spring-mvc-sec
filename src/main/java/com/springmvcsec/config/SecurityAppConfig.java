package com.springmvcsec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityAppConfig {

	@Bean
	public UserDetailsManager getUserDetails() {
		UserDetails userDetails1=User
				.withUsername("abhishek")
				.password("{noop}abhishek")
				.roles("admin","user")
				.build();
		
		return new InMemoryUserDetailsManager(userDetails1);
		
		
	}
	
}
