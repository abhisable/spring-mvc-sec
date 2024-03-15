package com.springmvcsec.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityAppConfig {

	@Autowired
	HttpSecurity httpSecurity;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Bean
	public UserDetailsManager getUserDetails(DataSource dataSource) {

//		UserDetails userDetail=User
//				.withUsername("ramu")
//				.password(passwordEncoder.encode("ramu"))
//				.roles("user")
//				.build();
//		
		JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
//		jdbcUserDetailsManager.createUser(userDetail);
		return jdbcUserDetailsManager;
		
	}
	
	@Bean
	public SecurityFilterChain settingUpSecurityFilterChain() throws Exception {
		

		httpSecurity.authorizeHttpRequests(customizer->{
			customizer.requestMatchers(AntPathRequestMatcher.antMatcher("/hello"),
					AntPathRequestMatcher.antMatcher("/helloworld"))
			.authenticated();
			
			customizer.requestMatchers(AntPathRequestMatcher.antMatcher("/bye"),
					AntPathRequestMatcher.antMatcher("/myCustomLogin"),
					AntPathRequestMatcher.antMatcher("/WEB-INF/view/*"),
					AntPathRequestMatcher.antMatcher("/signup"),
					AntPathRequestMatcher.antMatcher("/signup-processing"))
			.permitAll();
			
		});
		
		httpSecurity.formLogin(formLoginCustomizer->{
		formLoginCustomizer.loginPage("/myCustomLogin").permitAll();
	});
		httpSecurity.httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
	}
	
	
	
}
