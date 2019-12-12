package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Items")
public class Item {
	
	@Id
	private String id;
	private int cantidad;
	private Double total;
	
	@OneToOne(targetEntity = Producto.class)
	Producto producto;
	

}
