package com.example.demo.aplicacion;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dominio.model.Producto;
import com.example.demo.dominio.services.ProductoService;
import com.example.demo.infraestructura.dto.ProductoDto;
import com.example.demo.infraestructura.dto.ProductoRest;
import com.example.demo.infraestructura.mapper.ProductoMapper;
import com.example.demo.infraestructura.repository.database.ProductoRepository;

@Component
public class ProductoAplicacion {

	@Autowired
	ProductoService productoService;

	@Autowired
	ProductoMapper productoMapper;

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
