package com.app.myconame.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.myconame.exception.NoSuchPlanExistsException;

@RestControllerAdvice
public class NoSuchPlanExistsExceptionHandler {

	@ExceptionHandler(NoSuchPlanExistsException.class)
	public ResponseEntity<String> showExceptionMessage(NoSuchPlanExistsException nsfe){
		return new ResponseEntity<String>(nsfe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
