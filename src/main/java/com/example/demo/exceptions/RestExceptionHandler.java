package com.example.demo.exceptions;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ BadRequestException.class, 
			HttpMessageNotReadableException.class,
			HttpRequestMethodNotSupportedException.class,
			TypeMismatchException.class,
			InvalidFormatException.class,
			ConstraintViolationException.class,
			MissingServletRequestParameterException.class,
			MissingRequestHeaderException.class
			})
	@ResponseBody
	public ErrorMensaje malaPeticon(Exception exception) {
		System.out.println("bad rerquest");
		return new ErrorMensaje(exception, HttpStatus.BAD_REQUEST.value());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException excepcion) {
	    Map<String, String> errors = new HashMap<>();
	    excepcion.getBindingResult().getAllErrors().forEach((error) -> {
	        String nombreCampo = ((FieldError) error).getField();
	        String mensajeError = error.getDefaultMessage();
	        errors.put(nombreCampo, mensajeError);
	    });
	    return errors;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public ErrorMensaje notFoundException(Exception exception) {
		return new ErrorMensaje(exception, HttpStatus.NOT_FOUND.value());
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ConflictException.class)
	@ResponseBody
	public ErrorMensaje conflict(Exception exception) {
		return new ErrorMensaje(exception, HttpStatus.CONFLICT.value());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public ErrorMensaje exception(Exception exception) { // The error must be corrected
		return new ErrorMensaje(exception, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

}