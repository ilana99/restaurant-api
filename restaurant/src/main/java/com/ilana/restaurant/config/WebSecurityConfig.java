package com.ilana.restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	// @formatter:off
		@Bean
		SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.csrf((csrf) -> csrf.disable())
					.authorizeHttpRequests((authorize) -> authorize
							.anyRequest().permitAll())
					.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.cors(cors -> cors.disable());
			
			return http.build();
		}
		
	
}
