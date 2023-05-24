package com.spring.security.backend.api.dto.exception;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@Getter
public class ExceptionDto {

    @JsonIgnore
    private static final String SERVICE_NAME = "be-spring-security";
    @JsonIgnore
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

    private String service;
    private String path;
    private String error;
    private Integer status;
    private String timestamp;

    public ExceptionDto(Integer status, String error, HttpServletRequest request) {
        this.service = SERVICE_NAME;
        this.error = error;
        this.status = status;
        this.path = request.getRequestURI();
        this.timestamp = getTimeStamp(DATE_TIME_PATTERN);
    }

    private String getTimeStamp(String dateTimePattern) {
        return ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern(dateTimePattern));
    }
}