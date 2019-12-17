package com.example.demo.infraestructura.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaRest {

	private String id;
	private Double total;
	private String cliente;
	private Long telefono;
	private List<ItemRest> item;

}
