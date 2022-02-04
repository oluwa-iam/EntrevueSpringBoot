package com.example.entrevueSpringBoot.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class EntrevueExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Handle all exception when there is no specific Handler for the error
	 * @param ex the exception
	 * @param request The request being processed
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		EntrevueException entrevueException = new EntrevueException(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(entrevueException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Handle Not found Exception for Film Entity
	 * @param ex
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(FilmNotFoundException.class)
	public final ResponseEntity<Object> handleFilmNotFoundException(Exception ex, WebRequest request) throws Exception {
		EntrevueException entrevueException = new EntrevueException(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(entrevueException, HttpStatus.NOT_FOUND);
	}
	

}
