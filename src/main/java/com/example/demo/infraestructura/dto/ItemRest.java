package com.example.demo.infraestructura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRest {

	private String id;
	private int cantidad;
	private Double total;
	private ProductoRest producto;

}
