package com.example.demo.dominio.model;

import com.example.demo.shared.dominio.Cliente;
import com.example.demo.shared.dominio.Telefono;
import com.example.demo.shared.dominio.Total;

public class Factura {

	private final Total total;
	private final Cliente cliente;
	private final Telefono telefono;

	public static Factura of(Total total, Cliente cliente, Telefono telefono) {
		return new Factura(total, cliente, telefono);
	}

	public Factura(Total total, Cliente cliente, Telefono telefono) {
		super();
		this.total = total;
		this.cliente = cliente;
		this.telefono = telefono;
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

}
