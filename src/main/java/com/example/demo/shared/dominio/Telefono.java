package com.example.demo.shared.dominio;

import com.example.demo.exceptions.TelefonoException;

public class Telefono {

	private final Long telefono;

	public Telefono(Long valor) {
		try {
			this.telefono = valor;
			if (valor == 0) {
				throw new TelefonoException();
			}
		} catch (NullPointerException e) {
			throw new TelefonoException();
		}
	}

	public Long getTelefono() {
		return telefono;
	}

}
