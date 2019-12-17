package com.example.demo.exceptions;

public class CantidadException extends RuntimeException {
	public CantidadException() {
		super("Error: Cantidad producto");
	}
}
