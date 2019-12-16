package com.example.demo.aplicacion;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dominio.model.Producto;
import com.example.demo.dominio.services.ProductoService;
import com.example.demo.infraestructura.dto.ProductoDto;
import com.example.demo.infraestructura.mapper.ProductoMapper;
import com.example.demo.infraestructura.repository.database.ProductoRepository;

@Component
public class ProductoAplicacion {

	@Autowired
	ProductoService productoService;

	public void crear(Producto p) {
		productoService.guardar(p);
	}

	public List<Producto> listar() {
//		return productoService.buscarTodos().stream()
//				.map(producto -> productoMaper.apitransformarDominioParaDto(producto)).collect(Collectors.toList());
		return productoService.buscarTodos();
	}

	public Producto buscar(String codigo) {
//		return productoService.findById(codigo).orElseThrow(() -> new RegistroNoEncontradoException());
		return productoService.buscarPorId(codigo);
	}

	public void eliminar(String codigo) {
//		ProductoDto p = productoService.findById(codigo).orElseThrow(() -> new NoEliminadoHandleException());
//		productoService.deleteById(p.getId());
		productoService.eliminarPorId(codigo);
	}

	public void actualizar(Producto p) {
//		productoService.findById(p.getId()).orElseThrow(() -> new EditadoHandlerException());
//		productoService.save(p);
		productoService.editar(p);
	}

}
