package com.example.demo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aplicacion.ProductoAplicacion;
import com.example.demo.infraestructura.dto.ProductoRest;
import com.example.demo.infraestructura.mapper.ProductoMapper;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ProductoAplicacion productoAplicacion;

	@GetMapping
	public List<ProductoRest> getProductos() {
		return productoAplicacion.listar();
	}

	@GetMapping("/{id}")
	public ProductoRest getProducto(@PathVariable String id) {
		return productoAplicacion.buscar(id);
	}

	@PostMapping
	public void save(@RequestBody ProductoRest producto) {
		productoAplicacion.crear(producto);
	}

	@PutMapping
	public void edit(@RequestBody ProductoRest producto) {
		productoAplicacion.actualizar(producto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		productoAplicacion.eliminar(id);
	}

}
