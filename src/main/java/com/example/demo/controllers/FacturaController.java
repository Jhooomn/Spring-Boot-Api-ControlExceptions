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

import com.example.demo.dto.Factura;
import com.example.demo.repository.FacturaRepository;

@RestController
@RequestMapping("/factura")
public class FacturaController {

	@Autowired
	FacturaRepository facturaRepository;

	@GetMapping()
	public List<Factura> getFacturas() {
		return facturaRepository.findAll();
	}

	@GetMapping("/{id}")
	public Factura getFactura(@PathVariable String id) {
		return facturaRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}
	
	@PostMapping()
	public void addFactura(@RequestBody Factura factura) {
		facturaRepository.save(factura);
	}
	
	@PutMapping()
	public void editFactura(@RequestBody Factura factura) {
		facturaRepository.findById(factura.getId()).orElseThrow( () -> new RuntimeException());
		facturaRepository.save(factura);
	}
	
	@DeleteMapping("/{id}")
	public void deleteFactura(@PathVariable String id) {
		facturaRepository.findById(id).orElseThrow( () ->   new RuntimeException());
		facturaRepository.deleteById(id);
	}
	
}
