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

		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		
//      jdbcUserDetailsManager.setUsersByUsernameQuery("select username,password,enabled from customers where username = ?"); this way we can override all the default schema queries according to our need
//		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username,roles from customers where username = ?");
		
		return jdbcUserDetailsManager;

	}

	@Bean
	public SecurityFilterChain settingUpSecurityFilterChain() throws Exception {

		httpSecurity.authorizeHttpRequests(customizer -> {
			customizer.requestMatchers(AntPathRequestMatcher.antMatcher("/WEB-INF/view/*"),
					AntPathRequestMatcher.antMatcher("/signup"), AntPathRequestMatcher.antMatcher("/singup-processing"))
					.permitAll();
			customizer.requestMatchers(AntPathRequestMatcher.antMatcher("/coder")).hasAuthority("Coder");
			customizer.requestMatchers(AntPathRequestMatcher.antMatcher("/trainer")).hasAuthority("Trainer");
			customizer.anyRequest().authenticated();

		});

		httpSecurity.formLogin(formLoginCustomizer -> {
			formLoginCustomizer.loginPage("/myCustomLogin").permitAll();
		});
		httpSecurity.httpBasic(Customizer.withDefaults());

		httpSecurity.logout(logoutCustomizer -> {
			logoutCustomizer.logoutSuccessUrl("/myCustomLogin");
		});

		httpSecurity.exceptionHandling(customizer -> {
			customizer.accessDeniedPage("/accessDenied");
		});

		return httpSecurity.build();
	}

}
