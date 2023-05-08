package com.spring.security.backend.security.filter;

import static com.spring.security.backend.security.filter.util.FilterLogFormatUtil.getFormatedExceptionLogPart;
import static com.spring.security.backend.security.filter.util.FilterLogFormatUtil.getFormatedTitle;

import java.io.IOException;

import javax.xml.ws.http.HTTPException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecondFilter extends OncePerRequestFilter {

	private final static String FILTER_LOG_TITLE = getFormatedTitle(SecondFilter.class.getSimpleName());
	private final static String EXCEPTION_LOG_PART = getFormatedExceptionLogPart(SecondFilter.class.getSimpleName());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, HTTPException {

		log.info(FILTER_LOG_TITLE);

		String testHeader = request.getHeader("Authorization");

		filterChain.doFilter(request, response);

	}
}