package com.spring.security.backend.security.config;

import static com.spring.security.backend.security.config.BaseSecurityDslConfig.baseSecurityDslConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.spring.security.backend.security.filter.SecondFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
		return manager;
	}

	@Bean
	@Order(1)
	public SecurityFilterChain publicFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/**").authorizeHttpRequests(a -> a.requestMatchers("/**").permitAll())
				.apply(baseSecurityDslConfig());
		return http.build();
	}

	@Bean
	@Order(0)
	public SecurityFilterChain privateFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/private/**").authorizeHttpRequests(a -> a.requestMatchers("/private/**").permitAll())
				.addFilterAfter(new SecondFilter(), LogoutFilter.class)
				.apply(baseSecurityDslConfig());
		return http.build();
	}
}