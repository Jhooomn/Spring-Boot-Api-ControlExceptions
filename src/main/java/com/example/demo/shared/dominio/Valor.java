package com.example.demo.shared.dominio;

import com.example.demo.exceptions.PrecioException;

public class Valor {

	public Valor(Double valor) {
		if (valor <= 0) {
			throw new PrecioException();
		}
	}

}
