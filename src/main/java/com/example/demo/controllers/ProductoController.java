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

import com.example.demo.dominio.services.ProductoService;
import com.example.demo.exceptions.EditadoHandlerException;
import com.example.demo.exceptions.NoEliminadoHandleException;
import com.example.demo.exceptions.RegistroNoEncontradoException;
import com.example.demo.infraestructura.dto.ProductoDto;
import com.example.demo.repository.productoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	private ProductoService productoService;

	@PostMapping()
	public void crear(@RequestBody ProductoDto p) {
		productoService.guardar(p);
	}

	@GetMapping()
	public List<ProductoDto> listar() {
		return productoService.findAll();
	}

	@GetMapping("/{codigo}")
	public ProductoDto buscar(@PathVariable String codigo) {
		return productoService.findById(codigo).orElseThrow(() -> new RegistroNoEncontradoException());
	}

	@DeleteMapping("/{codigo}")
	public void eliminar(@PathVariable String codigo) {
		ProductoDto p = productoService.findById(codigo).orElseThrow(() -> new NoEliminadoHandleException());
		productoService.deleteById(p.getId());
	}

	@PutMapping
	public void actualizar(@RequestBody ProductoDto p) {
		productoService.findById(p.getId()).orElseThrow(() -> new EditadoHandlerException());
		productoService.save(p);
	}

}
