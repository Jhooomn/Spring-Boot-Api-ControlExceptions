package com.example.demo.shared.dominio;

import com.example.demo.exceptions.NombreCaracterException;

public class Cliente {

	private final String nombre;

	public Cliente(String valor) {

		try {
			this.nombre = valor;
			if ((valor.length() == 0) || (!nombre.matches("[A-Z].*"))) {
				throw new NombreCaracterException();
			}

		} catch (NullPointerException e) {
			throw new NombreCaracterException();
		}

	}

	public String getNombre() {
		return nombre;
	}

}
