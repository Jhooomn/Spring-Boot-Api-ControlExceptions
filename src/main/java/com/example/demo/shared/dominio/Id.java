package com.example.demo.shared.dominio;

import com.example.demo.exceptions.IdException;

public class Id {

	public Id(String id) {

		if ((id.length() > 64) || (id.length() < 32)) {
			throw new IdException();
		}

	}

}
