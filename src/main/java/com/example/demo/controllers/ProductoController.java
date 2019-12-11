package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
import com.example.demo.exceptions.ExceptionsHandler;
import com.example.demo.exceptions.NoEliminadoHandleException;
import com.example.demo.exceptions.RegistroNoEncontradoException;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	private List<Producto> repositorio = new ArrayList<>();

	@PostMapping()
	public void crear(@RequestBody Producto p) {
		repositorio.add(p);
	}

	@GetMapping()
	public List<Producto> listar() {
		return repositorio;
	}

	@GetMapping("/{codigo}")
	public Producto buscar(@PathVariable String codigo) {
		return repositorio.stream().filter(producto -> producto.getCodigo().matches(codigo)).findAny()
				.orElseThrow(() -> new RegistroNoEncontradoException());
	}

	@DeleteMapping("/{codigo}")
	public void eliminar(@PathVariable String codigo) {
		// repositorio.removeIf(producto ->
		// producto.getCodigo().equalsIgnoreCase(codigo));
		 Producto producto =  repositorio.stream().filter(x -> x.getCodigo().matches(codigo)).findAny()
				.orElseThrow(() -> new NoEliminadoHandleException());
		 repositorio.remove(repositorio.indexOf(producto));
	}

	@PutMapping
	public void actualizar(@RequestBody Producto p) {
//		repositorio.forEach(pro -> {
//			if(pro.getCodigo().equalsIgnoreCase(p.getCodigo())) {
//				pro.setNombre(p.getNombre());
//				pro.setValor(p.getValor());
//			}
//		});
		Producto producto = repositorio.stream().filter(x -> x.getCodigo().matches(p.getCodigo())).findAny()
				.orElseThrow(() -> new EditadoHandlerException());
		repositorio.set(repositorio.indexOf(producto), p);
	}

}
