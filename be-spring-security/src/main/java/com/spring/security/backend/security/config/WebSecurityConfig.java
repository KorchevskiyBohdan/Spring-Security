package com.spring.security.backend.security.config;

import static com.spring.security.backend.security.config.BaseSecurityDslConfig.baseSecurityDslConfig;

import static com.spring.security.backend.security.authentication.AuthenticationConfig.employeesAuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithms;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import com.spring.security.backend.security.filter.SecondFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

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
		http.securityMatcher("/private/**").authorizeHttpRequests(a -> a.requestMatchers("/private/**").authenticated())
				.addFilterAfter(new SecondFilter(), LogoutFilter.class)
				.apply(baseSecurityDslConfig());
		
		// Use RequestCacheAwareFilter to save request of unauthentificated user
		http.requestCache(cache -> cache.requestCache(new HttpSessionRequestCache()));
		
		// Resolve authentification manager (for example: employee and consumer authentification) use:
		//.authenticationManagerResolver(authenticationManagerResolver()).jwt();
		
		http.oauth2ResourceServer(oauth2 -> oauth2.bearerTokenResolver(new DefaultBearerTokenResolver())
				.jwt(jwt -> jwt.decoder(JwtDecoders.fromIssuerLocation("http://localhost:8080/api/wizard/auth/issuer"))));
		
		return http.build();
	}
}