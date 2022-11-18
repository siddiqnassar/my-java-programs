package com.nassar.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nassar.employee.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ErrorResponse.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse employeeNotFound(ErrorResponse err) {
	
	return new ErrorResponse(HttpStatus.NOT_FOUND.value() , "employee Not Found");	
	}
	
}
