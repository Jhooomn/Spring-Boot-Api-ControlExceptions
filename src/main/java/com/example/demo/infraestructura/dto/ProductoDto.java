package com.example.demo.infraestructura.dto;

import javax.persistence.Entity;

import javax.persistence.Table;

import com.example.demo.dto.BaseEntity;

@Entity
@Table(name = "Productos")
public class ProductoDto extends BaseEntity {

	private String nombre;
	private Double valor;

	public ProductoDto() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
