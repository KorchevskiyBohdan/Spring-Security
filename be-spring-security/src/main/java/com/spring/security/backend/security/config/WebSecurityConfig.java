package com.spring.security.backend.security.config;

import static com.spring.security.backend.security.authentication.ImplAuthenticationEntryPoint.implAuthenticationEntryPoint;
import static com.spring.security.backend.security.config.BaseSecurityDslConfig.baseSecurityDslConfig;
import static com.spring.security.backend.security.filter.AuthenticationConverterFilter.authenticationConverterFilter;
import static org.springframework.security.oauth2.jwt.JwtDecoders.fromIssuerLocation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Value("${oath2.issuer.url}")
    private String issuerUrl;

    @Bean
    @Order(1)
    public SecurityFilterChain publicFilterChain(HttpSecurity http) throws Exception {

        return http.securityMatcher("/**")
            .apply(baseSecurityDslConfig()).and()
            .authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll())
            .build();
    }

    @Bean
    @Order(0)
    public SecurityFilterChain privateFilterChain(HttpSecurity http) throws Exception {

        return http.securityMatcher("/private/**")
            .apply(baseSecurityDslConfig()).and()
            .requestCache(cache -> cache.requestCache(httpSessionRequestCache()))
            .authorizeHttpRequests(auth -> auth.requestMatchers("/private/**").authenticated())
            .oauth2ResourceServer(oauth2 -> oauth2.authenticationEntryPoint(implAuthenticationEntryPoint())
                .bearerTokenResolver(defaultBearerTokenResolver())
                .jwt(jwt -> jwt.decoder(fromIssuerLocation(issuerUrl))))
            .addFilterAfter(authenticationConverterFilter(), AuthorizationFilter.class)
            .build();
    }

    private static HttpSessionRequestCache httpSessionRequestCache() {
        return new HttpSessionRequestCache();
    }

    private static DefaultBearerTokenResolver defaultBearerTokenResolver() {
        return new DefaultBearerTokenResolver();
    }
}