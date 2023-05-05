package com.spring.security.backend.security.config;

import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.session.ForceEagerSessionCreationFilter;

import com.spring.security.backend.security.filter.ExceptionHandlerFilter;

public class BaseDslConfig extends AbstractHttpConfigurer<BaseDslConfig, HttpSecurity> {

	@Override
	public void init(HttpSecurity http) throws Exception {
		// any method that adds another configurer
		// must be done in the init method
		http.csrf().disable();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		ApplicationContext appContext = http.getSharedObject(ApplicationContext.class);

		ExceptionHandlerFilter exceptionHandlerFilter = appContext.getBean(ExceptionHandlerFilter.class);
		http.addFilterBefore(exceptionHandlerFilter, LogoutFilter.class);
	}

	public static BaseDslConfig baseDslConfig() {
		return new BaseDslConfig();
	}
}