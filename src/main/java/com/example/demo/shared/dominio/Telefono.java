package com.example.demo.shared.dominio;

public class Telefono {

	private final Long telefono;

	public Telefono(Long valor) {
		try {
			this.telefono = valor;
			if (valor == 0) {
				throw new RuntimeException();
			}
		} catch (NullPointerException e) {
			throw new RuntimeException();
		}
	}

	public Long getTelefono() {
		return telefono;
	}

}
