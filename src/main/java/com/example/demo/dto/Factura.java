package com.example.demo.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.infraestructura.dto.ItemDto;

@Entity
@Table(name = "Facturas")
public class Factura extends BaseEntity {

	private Double total;

	private String cliente;

	private Long telefono;

	@OneToMany(targetEntity = ItemDto.class, cascade = CascadeType.ALL)
	private List<ItemDto> item;

	public Factura() {
		// TODO Auto-generated constructor stub
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

	public List<ItemDto> getItem() {
		return item;
	}

	public void setItem(List<ItemDto> item) {
		this.item = item;
	}

}
