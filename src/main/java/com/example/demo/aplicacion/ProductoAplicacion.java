package com.example.demo.aplicacion;

import java.util.List;
import java.util.UUID;
import com.example.demo.dominio.services.ProductoService;
import com.example.demo.infraestructura.dto.ProductoRest;
import com.example.demo.infraestructura.mapper.ProductoMapper;

public class ProductoAplicacion {

	ProductoService productoService;

	ProductoMapper productoMapper;

	public ProductoAplicacion(ProductoService productoService, ProductoMapper productoMapper) {
		this.productoService = productoService;
		this.productoMapper = productoMapper;
	}

	public void crear(ProductoRest p) {
		if (p.getId() == null) {
			p.setId(UUID.randomUUID().toString());
		}
		productoService.guardar(productoMapper.apitransformarDtoParaDominio(p));
	}

	public List<ProductoRest> listar() {
		return productoMapper.apitransformarListDominioParaDto(productoService.buscarTodos());
	}

	public ProductoRest buscar(String codigo) {
		return productoMapper.apitransformarDominioParaDto(productoService.buscarPorId(codigo));
	}

	public void eliminar(String codigo) {
		productoService.eliminarPorId(codigo);
	}

	public void actualizar(ProductoRest p) {
		productoService.editar(productoMapper.apitransformarDtoParaDominio(p));
	}

}
