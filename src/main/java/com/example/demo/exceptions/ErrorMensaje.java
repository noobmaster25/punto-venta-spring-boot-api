package com.example.demo.exceptions;

import lombok.Getter;

@Getter
public class ErrorMensaje {

	private String mensaje;
	private String excepcion;
	private Integer code;
	
	public ErrorMensaje(Exception ex , Integer code) {
		this.excepcion = ex.getClass().getName();
		this.mensaje = ex.getMessage();
		this.code = code;
	}
	
}
