
package com.codestates.hobby.global.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
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
		log.warn("MethodArgumentNotValidException occurred: {}", e.getMessage(), e);
		return ErrorResponse.of(e.getBindingResult());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse ConstraintViolationException(ConstraintViolationException e) {
		log.warn("ConstraintViolationException occurred: {}", e.getMessage(), e);
		return ErrorResponse.of(e.getConstraintViolations());
	}

	@ExceptionHandler({
		IllegalStateException.class, IllegalArgumentException.class,
		TypeMismatchException.class, HttpMessageNotReadableException.class,
		MissingServletRequestParameterException.class, MultipartException.class,
	})
	public ErrorResponse handleBadRequestException(Exception e) {
		log.warn("Bad request exception occurred: {}", e.getMessage(), e);
		return ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public ErrorResponse handleMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
		log.warn("MediaTypeNotSupportedException: {}", e.getMessage(), e);
		return ErrorResponse.of(HttpStatus.UNSUPPORTED_MEDIA_TYPE, e.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.warn("Request method not supported exception: {}", e.getMessage(), e);
		return ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
	}

	@ExceptionHandler
	public ResponseEntity<?> handleBusinessLogicException(BusinessLogicException e) {
		log.warn("Business logic exception occurred: {}", e.getMessage(), e);
		HttpStatus status = HttpStatus.valueOf(e.getExceptionCode().getStatus());
		return new ResponseEntity<>(ErrorResponse.of(status, e.getExceptionCode().getMessage()), status);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException(Exception e) {
		log.error("Exception occurred: {}", e.getMessage(), e);
		return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}
}
