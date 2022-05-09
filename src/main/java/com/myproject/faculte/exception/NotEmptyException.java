package com.myproject.faculte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEmptyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotEmptyException(String string) {
		super(string);
		
	}
}