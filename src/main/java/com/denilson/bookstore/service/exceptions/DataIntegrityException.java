package com.denilson.bookstore.service.exceptions;

public class  DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public  DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public  DataIntegrityException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
