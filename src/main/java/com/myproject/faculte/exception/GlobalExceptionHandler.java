package com.myproject.faculte.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//manipulation exceptions spécifiques
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException execption,WebRequest request){
		ErrorDetails errorDetails=new ErrorDetails(new Date(),execption.getMessage(),request.getDescription(false));
	return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException execption,WebRequest request){
		ErrorDetails errorDetails=new ErrorDetails(new Date(),execption.getMessage(),request.getDescription(false));
	return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException execption,WebRequest request){
		ErrorDetails errorDetails=new ErrorDetails(new Date(),execption.getMessage(),request.getDescription(false));
	return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
		}
	
	  @ExceptionHandler(ApiException.class) public ResponseEntity<?>
	  handleApiException(ApiException execption,WebRequest request){ ErrorDetails
	  errorDetails=new ErrorDetails(new
	  Date(),execption.getMessage(),request.getDescription(false)); return new
	  ResponseEntity(errorDetails,HttpStatus.NOT_FOUND); }
	 
	
	/*
	 * @ExceptionHandler(NotEmptyException.class) public ResponseEntity<?>
	 * handleNotEmptyException(NotEmptyException execption,WebRequest request){
	 * ErrorDetails errorDetails=new ErrorDetails(new
	 * Date(),execption.getMessage(),request.getDescription(false)); return new
	 * ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST); }
	 */
	  
	  
	  @ExceptionHandler(HttpMessageNotReadableException.class)
		public ResponseEntity<?> handleNumberHttpMessageNotReadableException(HttpMessageNotReadableException execption,WebRequest request){
			ErrorDetails errorDetails=new ErrorDetails(new Date(),execption.getMessage(),request.getDescription(false));
		return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
		}
	  @ExceptionHandler(NumberFormatException.class)
		public ResponseEntity<?> handleNumberFormatException(NumberFormatException execption,WebRequest request){
			ErrorDetails errorDetails=new ErrorDetails(new Date(),execption.getMessage(),request.getDescription(false));
		return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
		}
	  
	  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
		public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException execption,WebRequest request){
			ErrorDetails errorDetails=new ErrorDetails(new Date(),execption.getMessage(),request.getDescription(false));
		return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
		}
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException execption,WebRequest request){
		ErrorDetails errorDetails=new ErrorDetails(new Date(),execption.getMessage(),request.getDescription(false));
	return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException execption,WebRequest request){
		ErrorDetails errorDetails=new ErrorDetails(new Date(),execption.getMessage(),request.getDescription(false));
	return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	
	  @ExceptionHandler(MethodArgumentNotValidException.class) public
	  ResponseEntity<?>
	  handleMethodArgumentNotValidException(MethodArgumentNotValidException execption,WebRequest request){
		  ErrorDetails errorDetails=new ErrorDetails(new Date(),execption.getMessage(),request.getDescription(false)); 
		  return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
		  }
	 
	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public Map<String,
	 * String> handleMethodArgumentNotValidException(MethodArgumentNotValidException
	 * execption){ Map<String, String> errMap=new HashMap<>();
	 * execption.getBindingResult().getFieldErrors().forEach(error->{
	 * errMap.put(error.getField(), error.getDefaultMessage()); }); return errMap; }
	 */
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException execption,WebRequest request){
		ErrorDetails errorDetails=new ErrorDetails(new Date(),"ce champ ne peut pas être vide ou null",request.getDescription(false));
	return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	//manipulation  exceptions globales
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception execption,WebRequest request){
		ErrorDetails errorDetails=new ErrorDetails(new Date(),execption.getMessage(),request.getDescription(false));
	return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
