package com.app.myconame.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.myconame.exception.NoSuchStatusExistsException;

@RestControllerAdvice
public class NoSuchStatusExistsExceptionHandler {

	@ExceptionHandler(NoSuchStatusExistsException.class)
	public ResponseEntity<String> showExceptionMessage(NoSuchStatusExistsException nsse){
		return new ResponseEntity<String>(nsse.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
