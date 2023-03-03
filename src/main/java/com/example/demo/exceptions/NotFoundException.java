package com.example.demo.exceptions;

public class NotFoundException extends RuntimeException{


	private static final long serialVersionUID = 6871623339563527956L;
	
	private static final String DESCRIPCION = "Bad reques exception (400)";

	public NotFoundException(String detail) {
		super(DESCRIPCION + ". 	" + detail);
	}
}
