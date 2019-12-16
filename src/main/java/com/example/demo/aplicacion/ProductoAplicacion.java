package com.example.demo.aplicacion;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dominio.services.ProductoService;
import com.example.demo.infraestructura.dto.ProductoDto;
import com.example.demo.infraestructura.mapper.ProductoMapper;

public class ProductoAplicacion {

	@Autowired
	ProductoService productoService;

	@Autowired
	ProductoMapper productoMaper;

	public void crear(ProductoDto p) {
		if (p.getId() == null) {
			p.setId(UUID.randomUUID().toString());
		}
		productoService.guardar(productoMaper.apitransformarDtoParaDominio(p));
	}

	public List<ProductoDto> listar() {
		return productoService.buscarTodos().stream()
				.map(producto -> productoMaper.apitransformarDominioParaDto(producto)).collect(Collectors.toList());
	}

	public ProductoDto buscar(String codigo) {
//		return productoService.findById(codigo).orElseThrow(() -> new RegistroNoEncontradoException());
		return productoMaper.apitransformarDominioParaDto(productoService.buscarPorId(codigo));
	}

	public void eliminar(String codigo) {
//		ProductoDto p = productoService.findById(codigo).orElseThrow(() -> new NoEliminadoHandleException());
//		productoService.deleteById(p.getId());
		productoService.eliminarPorId(codigo);
	}

	public void actualizar(ProductoDto p) {
//		productoService.findById(p.getId()).orElseThrow(() -> new EditadoHandlerException());
//		productoService.save(p);
		productoService.guardar(productoMaper.apitransformarDtoParaDominio(p));
	}

}
