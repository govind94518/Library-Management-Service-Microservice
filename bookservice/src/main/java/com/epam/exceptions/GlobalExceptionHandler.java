package com.epam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<String> bookNotFoundHandler(BookNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookAlreadyExistsException.class)
	public ResponseEntity<String> bookAlreadyExistsException(BookAlreadyExistsException exception) {
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.ALREADY_REPORTED);
	}

}
