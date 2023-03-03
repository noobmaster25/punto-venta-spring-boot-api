package com.example.demo.exceptions;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 6955401256490714204L;
	
	public static final String DESCRIPCION = "Bad Request Exception";

	public BadRequestException(String detalles) {
		super(DESCRIPCION + ". " + detalles);
	}

}
