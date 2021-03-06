package com.userdetails.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;




@RestController
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleExceptions(Exception ex,  WebRequest request)
	{
		ExceptionResponse response = new ExceptionResponse(ex.getMessage(),request.getDescription(false),new Date());
		
		return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> userNotFoundException(Exception ex, WebRequest request)
	{
		ExceptionResponse response = new  ExceptionResponse(ex.getMessage(),request.getDescription(false),new Date());
		return new ResponseEntity(response, HttpStatus.NOT_FOUND);
	}
	
	@Override
	 protected ResponseEntity<Object> handleMethodArgumentNotValid(
	  MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
	  WebRequest request) {
	  
	  ExceptionResponse response = new
	  ExceptionResponse("validation faild",ex.getBindingResult().toString(),new
	  Date()); return new ResponseEntity(response, status.BAD_REQUEST); }
	 
}
