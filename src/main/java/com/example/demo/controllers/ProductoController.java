package com.example.demo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	ProductoMapper productoMapper;

	@GetMapping
	public List<ProductoRest> getProductos() {
		return productoMapper.apitransformarListDominioParaDto(productoAplicacion.listar());
	}

	@GetMapping("/{id}")
	public ProductoRest getProducto(@PathVariable String id) {
		return productoMapper.apitransformarDominioParaDto(productoAplicacion.buscar(id));
	}

	@PostMapping
	public void save(@RequestBody ProductoRest producto) {
		if (producto.getId() == null) {
			producto.setId(UUID.randomUUID().toString());
		}
		productoAplicacion.crear(productoMapper.apitransformarDtoParaDominio(producto));
	}

	@PutMapping
	public void edit(@RequestBody ProductoRest producto) {
		productoAplicacion.actualizar(productoMapper.apitransformarDtoParaDominio(producto));
	}

}
