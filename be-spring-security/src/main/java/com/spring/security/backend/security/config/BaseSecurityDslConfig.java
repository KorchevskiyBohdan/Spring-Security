package com.spring.security.backend.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.session.DisableEncodeUrlFilter;

import com.spring.security.backend.security.filter.ExceptionHandlerFilter;

public class BaseSecurityDslConfig extends AbstractHttpConfigurer<BaseSecurityDslConfig, HttpSecurity> {

	@Override
	public void init(HttpSecurity http) throws Exception {
		http.csrf().disable();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.addFilterAfter(new ExceptionHandlerFilter(), DisableEncodeUrlFilter.class);
	}

	public static BaseSecurityDslConfig baseSecurityDslConfig() {
		return new BaseSecurityDslConfig();
	}
}