package com.example.demo.shared.dominio;

public class Cliente {

	private static String nombre;

	public Cliente(String valor) {

		try {
			this.nombre = valor;
			if (valor.length() == 0) {
				throw new RuntimeException();
			}

		} catch (NullPointerException e) {
			throw new RuntimeException();
		}

	}

	public static String getNombre() {
		return nombre;
	}

}
