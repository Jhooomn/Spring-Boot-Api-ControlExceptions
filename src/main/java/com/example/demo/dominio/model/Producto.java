package com.example.demo.dominio.model;

import com.example.demo.shared.dominio.Id;
import com.example.demo.shared.dominio.Nombre;
import com.example.demo.shared.dominio.Valor;

// son objetos de dominio que tienen una serie de reglas 
// reglas: validaciones
// DDL --> Domain Driven Design (Erick Evan)
// DDD --> Diseño dirigido por el dominio
public class Producto {

	private final Id id;
	private final Nombre nombre;
	private final Valor valor;

	public static Producto of(Id id, Nombre nombre, Valor valor) {
		return new Producto(id, nombre, valor);
	}

	public Producto(Id id, Nombre nombre, Valor valor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.valor = valor;
	}

	public Id getId() {
		return id;
	}

	public Nombre getNombre() {
		return nombre;
	}

	public Valor getValor() {
		return valor;
	}

}
