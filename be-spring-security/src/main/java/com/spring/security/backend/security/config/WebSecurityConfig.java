package com.spring.security.backend.security.config;

import static com.spring.security.backend.security.config.BaseDslConfig.baseDslConfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.spring.security.backend.security.filter.PFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	// Require authentication to every URL in your application
	// Generate a login form for you
	// Let the user with a Username of user and a Password of password authenticate
	// with form based authentication

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
		return manager;
	}

	@Bean
	@Qualifier("publicFilterChain")
	public SecurityFilterChain publicFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/public/**").authorizeHttpRequests(a -> a.requestMatchers("/public/**").permitAll())
				.apply(baseDslConfig());
		return http.build();
	}

	@Bean
	@Qualifier("privateFilterChain")
	public SecurityFilterChain privateFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/private/**").authorizeHttpRequests(a -> a.requestMatchers("/private/**").permitAll())
				.addFilterAfter(new PFilter(), LogoutFilter.class).apply(baseDslConfig());

		return http.build();
	}
}