package com.example.demo.exceptions;

public class ClienteException extends RuntimeException {

	public ClienteException() {
		super("Error: nombre del cliente");
	}
	
}
