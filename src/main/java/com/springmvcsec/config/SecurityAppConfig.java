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
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityAppConfig {

	@Autowired
	HttpSecurity httpSecurity;
	
	
	@Bean
	public UserDetailsManager getUserDetails(DataSource dataSource) {
		UserDetails userDetails1=User
				.withUsername("Ashwini")
				.password("{bcrypt}$2a$10$bIOQcqqwAsyZ09qNMQ3Pq.1kNfx90cGvYlQZxjoVt2j9TJMo/1y/.")//ashwini123
				.roles("admin","user")
				.build();
		
		UserDetails userDetails2=User
				.withUsername("Prasanna")
				.password("{bcrypt}$2a$10$abAYQ.NpTk9q/Y/A/JkrzO8U8VigiIUNfcZunmLYq3UDbk3tAuHPy")//prasanna123
				.roles("user")
				.build();
		
		JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		jdbcUserDetailsManager.createUser(userDetails1);
		jdbcUserDetailsManager.createUser(userDetails2);
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
					AntPathRequestMatcher.antMatcher("/WEB-INF/view/*"))
			.permitAll();
			
		});
		
		httpSecurity.formLogin(formLoginCustomizer->{
		formLoginCustomizer.loginPage("/myCustomLogin").permitAll();
	});
		httpSecurity.httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
	}
	
	
	
}
