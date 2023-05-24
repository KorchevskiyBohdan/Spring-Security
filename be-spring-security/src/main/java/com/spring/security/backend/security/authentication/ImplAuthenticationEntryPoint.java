package com.spring.security.backend.security.authentication;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImplAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        log.error(authException.getMessage());
        throw new BadCredentialsException("Authentication failed!");
    }

    public static ImplAuthenticationEntryPoint implAuthenticationEntryPoint() {
        return new ImplAuthenticationEntryPoint();
    }
}