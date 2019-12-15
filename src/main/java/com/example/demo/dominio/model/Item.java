package com.example.demo.dominio.model;

import com.example.demo.shared.dominio.Cantidad;
import com.example.demo.shared.dominio.Id;
import com.example.demo.shared.dominio.Total;

public class Item {

	private final Id id;
	private final Cantidad cantidad;
	private final Total total;

	public static Item of(Id id, Cantidad cantidad, Total total) {
		return new Item(id, cantidad, total);
	}

	public Item(Id id, Cantidad cantidad, Total total) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.total = total;
	}

	public Id getId() {
		return id;
	}

	public Cantidad getCantidad() {
		return cantidad;
	}

	public Total getTotal() {
		return total;
	}

}
