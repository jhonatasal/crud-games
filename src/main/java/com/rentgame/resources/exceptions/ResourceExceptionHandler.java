package com.rentgame.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rentgame.services.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandartError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandartError standartError	=  StandartError.builder()
										.timestamp(Instant.now())
										.status(status.value())
										.error(e.getMessage())
										.message(error)
										.path(request.getRequestURI())
										.method(request.getMethod())
										.build();
		
		return ResponseEntity.status(status).body(standartError);
	}
}
