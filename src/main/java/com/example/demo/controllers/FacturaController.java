package com.example.demo.controllers;

import java.util.ArrayList;
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
import com.example.demo.dto.Item;
import com.example.demo.dto.Producto;
import com.example.demo.exceptions.EditadoHandlerException;
import com.example.demo.exceptions.RegistroNoEncontradoException;
import com.example.demo.repository.FacturaRepository;
import com.example.demo.repository.ProductoRepository;

@RestController
@RequestMapping("/factura")
public class FacturaController {

	@Autowired
	FacturaRepository facturaRepository;

	@Autowired
	ProductoRepository productoRepository;

	@GetMapping()
	public List<Factura> getFacturas() {
		return facturaRepository.findAll();
	}

	@GetMapping("/{id}")
	public Factura getFactura(@PathVariable String id) {
		return facturaRepository.findById(id).orElseThrow(() -> new RegistroNoEncontradoException());
	}

	// ***********************************************************************************************

	@PostMapping()
	public void addFactura(@RequestBody Factura factura) {
		// Obtener todos los codigos de los items
		List<String> codigos = cargarCodigos(factura.getItem());
		// Obtener todos los productos
		List<Producto> productos = cargarProductos(codigos);
		// Obtener la información completa de los items
		cargarItems(productos, factura.getItem());//factura.setItem();
		// Obtener el valor de la Factura
		Double total = calcularValorFactura(factura);
		factura.setTotal(total);
		facturaRepository.save(factura);
	}

	public List<String> cargarCodigos(List<Item> items) {
		List<String> codigos = new ArrayList<>();
		for (Item item : items) {
			codigos.add(item.getId());
		}
		return codigos;
	}

	public List<Item> cargarItems(List<Producto> productos, List<Item> factura_items) {
		List<Item> items = new ArrayList<>();
		for (Producto p : productos) {
			for (Item i : factura_items) {
				if (p.getCodigo().equalsIgnoreCase(i.getId())) {
					i.setProducto(p);
					i.setTotal(i.getCantidad() * p.getValor());
					items.add(i);
				}
			}
		}
		return items;
	}

	public List<Producto> cargarProductos(List<String> codigo) {

		return productoRepository.findAllById(codigo);

	}

	public Double calcularValorFactura(Factura factura) {
		Double total = 0.0;
		for (Item item : factura.getItem()) {
			total = item.getTotal() + total;
		}
		System.out.println(total);
		return total;
	}

	// ***********************************************************************************************

	@PutMapping()
	public void editFactura(@RequestBody Factura factura) {
		facturaRepository.findById(factura.getId()).orElseThrow(() -> new EditadoHandlerException());
		facturaRepository.save(factura);
	}

	@DeleteMapping("/{id}")
	public void deleteFactura(@PathVariable String id) {
		facturaRepository.findById(id).orElseThrow(() -> new RegistroNoEncontradoException());
		facturaRepository.deleteById(id);
	}

	@DeleteMapping
	public void deleteAll() {
		facturaRepository.deleteAll();
	}
}
