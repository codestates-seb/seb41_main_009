package com.codestates.hobby.global.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import com.codestates.hobby.global.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse NotValidException(MethodArgumentNotValidException e) {
		return ErrorResponse.of(e.getBindingResult());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse ConstraintViolationException(ConstraintViolationException e) {
		return ErrorResponse.of(e.getConstraintViolations());
	}

	@ExceptionHandler({
		IllegalStateException.class, IllegalArgumentException.class,
		TypeMismatchException.class, HttpMessageNotReadableException.class,
		MissingServletRequestParameterException.class, MultipartException.class,
	})
	public ErrorResponse handleBadRequestException(Exception e) {
		log.debug("Bad request exception occurred: {}", e.getMessage(), e);
		return ErrorResponse.of(HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.debug("Request method not supported exception: {}", e.getMessage(), e);
		return ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler
	public ResponseEntity<?> handleBusinessLogicException(BusinessLogicException e) {
		log.debug("Business logic exception ocurred: {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.valueOf(e.getExceptionCode().getStatus()));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException(Exception e) {
		log.error("# handle Exception", e);
		return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
