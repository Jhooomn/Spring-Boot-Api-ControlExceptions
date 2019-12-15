package com.example.demo.dominio.model;

import java.util.List;

import com.example.demo.infraestructura.dto.ItemDto;
import com.example.demo.shared.dominio.Cliente;
import com.example.demo.shared.dominio.Id;
import com.example.demo.shared.dominio.Telefono;
import com.example.demo.shared.dominio.Total;

public class Factura {

	private final Id id;
	private final Total total;
	private final Cliente cliente;
	private final Telefono telefono;
	private final List<Item> item;

	public static Factura of(Id id, Total total, Cliente cliente, Telefono telefono, List<Item> item) {
		return new Factura(id, total, cliente, telefono, item);
	}

	public Factura(Id id, Total total, Cliente cliente, Telefono telefono, List<Item> item) {
		super();
		this.id = id;
		this.total = total;
		this.cliente = cliente;
		this.telefono = telefono;
		this.item = item;
	}

	public Id getId() {
		return id;
	}

	public Total getTotal() {
		return total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public List<Item> getItem() {
		return item;
	}

}
