package com.example.demo.shared.dominio;

import com.example.demo.exceptions.CantidadException;

public class Cantidad {

	private final int value;

	public Cantidad(int cantidad) {
		try {
			this.value = cantidad;
			if (value < 0) {
				throw new CantidadException();
			}
		} catch (NullPointerException e) {
			throw new CantidadException();
		}

	}

	public int getValue() {
		return value;
	}

}
