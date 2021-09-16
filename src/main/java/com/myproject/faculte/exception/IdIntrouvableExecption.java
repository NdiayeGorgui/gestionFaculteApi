package com.myproject.faculte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdIntrouvableExecption extends Exception {



	public IdIntrouvableExecption(String string) {
		super(string);
		
	}
}