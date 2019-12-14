package com.example.demo.shared.dominio;

import com.example.demo.exceptions.NombreCaracterException;

public class Nombre {
	private final String value;

	public String getValue() {
		return value;
	}

	public Nombre(String nombre) {
		try {
			this.value = nombre;
			if (!nombre.matches("[A-Z].*")) {
				throw new NombreCaracterException();

			}
		} catch (NullPointerException e) {
			throw new NombreCaracterException();
		}
	}

}
