package com.myproject.faculte.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ErrorDetails {
	private Date timestamp;
	private String messase;
	private String details;

}
