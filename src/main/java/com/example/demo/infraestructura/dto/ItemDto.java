package com.example.demo.infraestructura.dto;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.dto.BaseEntity;

@Entity
@Table(name = "Items")
public class ItemDto extends BaseEntity {

	private int cantidad;
	private Double total;

	@OneToOne(targetEntity = ProductoDto.class)
	private ProductoDto producto;

	public ItemDto() {
		// TODO Auto-generated constructor stub
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public ProductoDto getProducto() {
		return producto;
	}

	public void setProducto(ProductoDto producto) {
		this.producto = producto;
	}

}
