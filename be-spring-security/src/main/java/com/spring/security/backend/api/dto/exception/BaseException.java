package com.spring.security.backend.api.dto.exception;

import jakarta.servlet.ServletException;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

	private Integer status;
	
	public BaseException(String message, Integer status) {
		super(message);
		this.status = status;
	}
}
