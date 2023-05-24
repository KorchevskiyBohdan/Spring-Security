package com.spring.security.backend.api.dto.exception;

public class TestExceptionDto extends BaseException {

    public TestExceptionDto(String message, Integer status) {
        super(message, status);
    }
}
