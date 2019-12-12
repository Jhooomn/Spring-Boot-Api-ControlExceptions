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

import com.example.demo.dto.Producto;
import com.example.demo.exceptions.EditadoHandlerException;
import com.example.demo.exceptions.NoEliminadoHandleException;
import com.example.demo.exceptions.RegistroNoEncontradoException;
import com.example.demo.repository.ProductoRepository;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	private ProductoRepository productoRepository;

	@PostMapping()
	public void crear(@RequestBody Producto p) {
		productoRepository.save(p);
	}

	@GetMapping()
	public List<Producto> listar() {
		return productoRepository.findAll();
	}

	@GetMapping("/{codigo}")
	public Producto buscar(@PathVariable String codigo) {
		return productoRepository.findById(codigo).orElseThrow(() -> new RegistroNoEncontradoException());
	}

	@DeleteMapping("/{codigo}")
	public void eliminar(@PathVariable String codigo) {
		Producto p = productoRepository.findById(codigo).orElseThrow(() -> new NoEliminadoHandleException());
		productoRepository.deleteById(p.getCodigo());
	}

	@PutMapping
	public void actualizar(@RequestBody Producto p) {
		productoRepository.findById(p.getCodigo()).orElseThrow(() -> new EditadoHandlerException());
		productoRepository.save(p);
	}

}
