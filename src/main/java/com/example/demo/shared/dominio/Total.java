package com.example.demo.shared.dominio;

import com.example.demo.exceptions.TotalException;

public class Total {

	private final Double total;

	public Total(Double valor) {
		try {
			this.total = valor;

			if (valor < 0) {
				throw new TotalException();
			}

		} catch (NullPointerException e) {
			throw new TotalException();
		}
	}

	public Double getTotal() {
		return total;
	}

}
