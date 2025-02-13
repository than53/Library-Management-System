package com.thanproject.library_management_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.thanproject.library_management_system.service.impl.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private MyUserDetailsService userServiceDetails;
	
	
	@Autowired
	public WebSecurityConfig(MyUserDetailsService userServiceDetails) {
		this.userServiceDetails = userServiceDetails;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests( request ->
				request.requestMatchers("/**").permitAll()
				.anyRequest().authenticated())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.formLogin(login -> login.disable())
				.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
		daoAuth.setUserDetailsService(userServiceProvider());
		daoAuth.setPasswordEncoder(passwordProvider());
		return daoAuth;
	}
	
	@Bean
	public UserDetailsService userServiceProvider() {
		return userServiceDetails;
	}
	@Bean
	public PasswordEncoder passwordProvider() {
		return new BCryptPasswordEncoder();
	}
}
