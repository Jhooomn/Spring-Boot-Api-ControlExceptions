package com.example.demo.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
import com.example.demo.infraestructura.dto.ProductoDto;
import com.example.demo.infraestructura.mapper.ProductoMapper;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ProductoService productoService;

	@Autowired
	ProductoMapper productoMaper;

	@PostMapping()
	public void crear(@RequestBody ProductoDto p) {
		if (p.getId() == null) {
			p.setId(UUID.randomUUID().toString());
		}
		productoService.guardar(productoMaper.transformarDtoParaDominio(p));
	}

	@GetMapping()
	public List<ProductoDto> listar() {
		return productoService.buscarTodos().stream().map(producto -> productoMaper.transformarDominioParaDto(producto))
				.collect(Collectors.toList());
	}

	@GetMapping("/{codigo}")
	public ProductoDto buscar(@PathVariable String codigo) {
//		return productoService.findById(codigo).orElseThrow(() -> new RegistroNoEncontradoException());
		return productoMaper.transformarDominioParaDto(productoService.buscarPorId(codigo));
	}

	@DeleteMapping("/{codigo}")
	public void eliminar(@PathVariable String codigo) {
//		ProductoDto p = productoService.findById(codigo).orElseThrow(() -> new NoEliminadoHandleException());
//		productoService.deleteById(p.getId());
		productoService.eliminarPorId(codigo);
	}

	@PutMapping
	public void actualizar(@RequestBody ProductoDto p) {
//		productoService.findById(p.getId()).orElseThrow(() -> new EditadoHandlerException());
//		productoService.save(p);
		productoService.guardar(productoMaper.transformarDtoParaDominio(p));
	}

}
