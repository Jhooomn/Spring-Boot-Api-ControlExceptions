package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aplicacion.FacturaAplicacion;
import com.example.demo.dominio.services.FacturaService;
import com.example.demo.dominio.services.ProductoService;
import com.example.demo.infraestructura.dto.FacturaRest;
import com.example.demo.infraestructura.mapper.FacturaMapper;
import com.example.demo.infraestructura.mapper.ProductoMapper;
import com.example.demo.infraestructura.repository.database.FacturaRepository;

@RestController
@RequestMapping("/factura")
public class FacturaController {

	FacturaAplicacion facturaAplicacion;

	public FacturaController(@Autowired FacturaRepository facturaRepository, @Autowired FacturaService facturaService,
			@Autowired FacturaMapper facturaMapper, @Autowired ProductoService productoService,
			@Autowired ProductoMapper productoMapper) {
		this.facturaAplicacion = new FacturaAplicacion(facturaRepository, facturaService, facturaMapper,
				productoService, productoMapper);
	}

	@GetMapping
	public List<FacturaRest> getFacturas() {
		return facturaAplicacion.getFacturas();
	}

	@GetMapping("/{id}")
	public FacturaRest getFactura(@PathVariable String id) {
		return facturaAplicacion.getFactura(id);
	}

	@PostMapping
	public void saveFactura(@RequestBody FacturaRest factura) {
		facturaAplicacion.addFactura(factura);
	}

	@PutMapping
	public void editFactura(@RequestBody FacturaRest factura) {
		facturaAplicacion.editFactura(factura);
	}

	@DeleteMapping("/{id}")
	public void deleteFactura(@PathVariable String id) {
		facturaAplicacion.deleteFactura(id);
	}

	@DeleteMapping
	public void deleteFacturas() {
		facturaAplicacion.deleteAll();
	}

}
