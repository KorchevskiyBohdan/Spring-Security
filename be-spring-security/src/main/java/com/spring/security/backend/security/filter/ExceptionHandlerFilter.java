package com.spring.security.backend.security.filter;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.backend.api.dto.exception.BaseException;
import com.spring.security.backend.api.dto.exception.ExceptionDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			log.info("-------- ExceptionHandlerFilter --------");
			filterChain.doFilter(request, response);
		} catch (Throwable ex) {
			log.error("Exception: %s".formatted(ex.getMessage()));

			if (ex instanceof BaseException) {
				var baseException = (BaseException) ex;
				response.setStatus(baseException.getStatus());
				response.getWriter().write(toJson(new ExceptionDto(baseException.getStatus(), ex.getMessage(), request)));
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			}
		}
	}

	private String toJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		return new ObjectMapper().writeValueAsString(object);
	}
}
