package com.example.demo.exceptions;

public class RegistroNoEncontradoException extends RuntimeException{
	public RegistroNoEncontradoException() {
		super("Registro no encontrado");
	}
}
