package com.spring.security.backend.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.security.backend.security.filter.ExceptionHandlerFilter;
import com.spring.security.backend.security.filter.PFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class FilterConfiguration {

	/*private final ExceptionHandlerFilter exceptionHandlerFilter;
	private final AuthorizationFilter authorizationFilter;

	@Bean
	@Qualifier("publicFilterChain")
	public SecurityFilterChain publicFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/public/**").permitAll();

		http.addFilterBefore(exceptionHandlerFilter, ForceEagerSessionCreationFilter.class);

		return http.build();
	}
	
	@Bean
	@Qualifier("privateFilterChain")
	public SecurityFilterChain privateFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/private/**").permitAll();

		http.addFilterBefore(exceptionHandlerFilter, ForceEagerSessionCreationFilter.class);
		http.addFilterAfter(authorizationFilter, ExceptionHandlerFilter.class);

		return http.build();
	}*/
	
	@Bean
	public ExceptionHandlerFilter exceptionHandlerFilter() {
		return new ExceptionHandlerFilter();
	}

}
