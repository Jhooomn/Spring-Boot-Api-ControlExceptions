package com.example.demo.infraestructura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoRest {

	private String id;
	private String nombre;
	private String valor;

}
