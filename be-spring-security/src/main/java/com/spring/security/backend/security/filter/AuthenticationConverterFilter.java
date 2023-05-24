package com.spring.security.backend.security.filter;

import static com.spring.security.backend.api.transformer.EmployeeDtoTransformer.toEmployeeDto;
import static com.spring.security.backend.security.filter.util.FilterLogFormatUtil.getFormatedTitle;
import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.authenticated;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationConverterFilter extends OncePerRequestFilter {

    private final static String FILTER_LOG_TITLE = getFormatedTitle(AuthenticationConverterFilter.class.getSimpleName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info(FILTER_LOG_TITLE);

        Authentication oldAuthentication = getContext().getAuthentication();
        getContext().setAuthentication(authenticated(toEmployeeDto((Jwt) oldAuthentication.getPrincipal()),
                oldAuthentication.getCredentials(), oldAuthentication.getAuthorities()));

        filterChain.doFilter(request, response);
    }

    public static AuthenticationConverterFilter authenticationConverterFilter() {
        return new AuthenticationConverterFilter();
    }
}