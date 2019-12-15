package com.example.demo.shared.dominio;

public class Total {

	private final Double total;

	public Total(Double valor) {
		try {
			this.total = valor;

			if (valor < 0) {
				throw new RuntimeException();
			}

		} catch (NullPointerException e) {
			throw new RuntimeException();
		}
	}

	public Double getTotal() {
		return total;
	}

}
