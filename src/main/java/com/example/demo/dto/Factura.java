package com.example.demo.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Facturas")
public class Factura extends BaseEntity {

	private Double total;

	private String cliente;

	private Long telefono;

	@OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
	private List<Item> item;

	public Factura() {
		// TODO Auto-generated constructor stub
	}

	public Factura(Double total, String cliente, Long telefono, List<Item> item) {
		super();
		this.total = total;
		this.cliente = cliente;
		this.telefono = telefono;
		this.item = item;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

}
