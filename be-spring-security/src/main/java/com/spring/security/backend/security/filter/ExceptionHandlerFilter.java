package com.spring.security.backend.security.filter;

import static com.spring.security.backend.security.filter.util.FilterLogFormatUtil.getFormatedExceptionLogPart;
import static com.spring.security.backend.security.filter.util.FilterLogFormatUtil.getFormatedTitle;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.backend.api.dto.exception.BaseException;
import com.spring.security.backend.api.dto.exception.ExceptionDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final static String FILTER_LOG_TITLE = getFormatedTitle(ExceptionHandlerFilter.class.getSimpleName());
    private final static String EXCEPTION_LOG_PART = getFormatedExceptionLogPart(ExceptionHandlerFilter.class.getSimpleName());

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, JsonProcessingException {

        try {
            log.info(FILTER_LOG_TITLE);
            filterChain.doFilter(request, response);
        } catch (Throwable ex) {
            log.error("%s %s".formatted(EXCEPTION_LOG_PART, ex.getMessage()));

            if (ex.getCause() instanceof BaseException) {
                var baseException = (BaseException) ex.getCause();
                populateErrorResponse(baseException.getStatus(), baseException.getMessage(), request, response);
            } else if (ex instanceof BaseException) {
                var baseException = (BaseException) ex;
                populateErrorResponse(baseException.getStatus(), baseException.getMessage(), request, response);
            } else {
                populateErrorResponse(HttpStatus.OK.value(), ex.getMessage(), request, response);
            }
        }
    }

    private void populateErrorResponse(Integer status, String message, HttpServletRequest request,
            HttpServletResponse response) throws JsonProcessingException, IOException {

        response.setStatus(status);
        response.getWriter().write(toJson(new ExceptionDto(status, message, request)));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

    private String toJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        return new ObjectMapper().writeValueAsString(object);
    }
}
