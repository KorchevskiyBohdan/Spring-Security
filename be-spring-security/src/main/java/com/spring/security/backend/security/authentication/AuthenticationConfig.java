package com.spring.security.backend.security.authentication;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtDecoders;

import com.spring.security.backend.api.dto.employee.EmployeeDto;
import com.spring.security.backend.api.dto.exception.BaseException;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AuthenticationConfig {
	
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_ATRIBUTE = "Bearer";
    
    public static AuthenticationManager employeesAuthenticationManager() {
        return null;
    }
}
