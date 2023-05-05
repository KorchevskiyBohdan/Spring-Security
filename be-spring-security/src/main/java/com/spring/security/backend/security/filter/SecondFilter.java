package com.spring.security.backend.security.filter;

import java.io.IOException;

import javax.xml.ws.http.HTTPException;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.security.backend.api.dto.exception.TestExceptionDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecondFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, HTTPException{
		
		log.info("-------- SecondFilter --------");

		Integer a = 1;
		if (a == 1) {
			log.error("SecondFilter exception");
			throw new TestExceptionDto("SecondFilter exception", HttpStatus.OK.value());
		}

		filterChain.doFilter(request, response);
		
	}
}
