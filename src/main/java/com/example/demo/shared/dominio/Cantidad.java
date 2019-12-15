package com.example.demo.shared.dominio;

public class Cantidad {

	private final int value;

	public Cantidad(int cantidad) {
		try {
			this.value = cantidad;
			if (value < 0) {
				throw new RuntimeException();
			}
		} catch (NullPointerException e) {
			throw new RuntimeException();
		}

	}

	public int getValue() {
		return value;
	}

}
