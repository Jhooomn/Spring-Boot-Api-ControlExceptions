package com.example.demo.shared.dominio;

import com.example.demo.exceptions.NombreCaracterException;
import com.example.demo.exceptions.NombreMayusculaException;

public class Nombre {

	public Nombre(String nombre) {
		if (!nombre.matches("[A-Z].*")) {
			throw new NombreCaracterException();
		}
	}

}
