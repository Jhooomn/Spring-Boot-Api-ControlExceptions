package com.example.demo.infraestructura.repository.adapters;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dominio.model.Producto;
import com.example.demo.dominio.services.ProductoService;
import com.example.demo.exceptions.RegistroNoEncontradoException;
import com.example.demo.infraestructura.dto.ProductoDto;
import com.example.demo.infraestructura.mapper.ProductoMapper;
import com.example.demo.infraestructura.repository.database.ProductoRepository;
import com.example.demo.shared.dominio.Id;

@Service
public class ProductoAdapter implements ProductoService {

	@Autowired
	ProductoRepository productoRepository;

	@Autowired
	ProductoMapper productoMapper;

	@Override
	public List<Producto> buscarPorIds(List<Id> codigos) {
		// TODO Auto-generated method stub

		return productoRepository
				.findAllById(codigos.stream().map(codigo -> codigo.getValue()).collect(Collectors.toList())).stream()
				.map(producto -> productoMapper.transformarDtoParaDominio(producto)).collect(Collectors.toList());
	}

	@Override
	public void guardar(Producto producto) {
		// TODO Auto-generated method stub
		ProductoDto productodto = productoMapper.transformarDominioParaDto(producto);
		productoRepository.save(productodto);
	}

	@Override
	public void editar(Producto producto) {
		this.buscarPorId(producto.getId().toString());
		
		productoRepository.save(productoMapper.transformarDominioParaDto(producto));
	}

	@Override
	public Producto buscarPorId(String id) {
		ProductoDto producto = productoRepository.findById(id).get();
		return productoMapper.transformarDtoParaDominio(producto);
	}

	@Override
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		return productoRepository.findAll().stream().map(producto -> producto).collect(Collectors.toList()).stream()
				.map(p -> productoMapper.transformarDtoParaDominio(p)).collect(Collectors.toList());
	}

	@Override
	public void eliminarPorId(String id) {
		ProductoDto producto = productoRepository.findById(id).get();
		productoRepository.deleteById(producto.getId());
	}

}
