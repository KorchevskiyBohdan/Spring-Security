package com.spring.security.backend.security.config;

import static com.spring.security.backend.security.config.BaseSecurityDslConfig.baseSecurityDslConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

import com.spring.security.backend.security.filter.SecondFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
		return manager;
	}

	@Bean
	public SecurityFilterChain publicFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/public/**").authorizeHttpRequests(a -> a.requestMatchers("/public/**").permitAll())
				.apply(baseSecurityDslConfig());
		return http.build();
	}

	@Bean
	public SecurityFilterChain privateFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/private/**").authorizeHttpRequests(a -> a.requestMatchers("/private/**").permitAll())
				.addFilterBefore(new SecondFilter(), WebAsyncManagerIntegrationFilter.class)
				.apply(baseSecurityDslConfig());
		return http.build();
	}
}