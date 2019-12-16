package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aplicacion.ProductoAplicacion;
import com.example.demo.infraestructura.dto.ProductoRest;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ProductoAplicacion productoAplicacion;

	@GetMapping
	public List<ProductoRest> getProductos() {
		return null;
	}

}
