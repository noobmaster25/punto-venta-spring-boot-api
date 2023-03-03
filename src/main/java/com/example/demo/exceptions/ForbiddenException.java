package com.example.demo.exceptions;

public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 7399930587522982360L;
	
	public static final String DESCRIPTION = "Forbiddend Exception";

	public ForbiddenException(String detail) {
		super(DESCRIPTION + ". " + detail);

	}
}
