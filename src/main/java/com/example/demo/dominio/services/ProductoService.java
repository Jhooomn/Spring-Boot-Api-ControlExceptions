package com.example.demo.dominio.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dominio.model.Producto;
import com.example.demo.shared.dominio.Id;

// Productos de dominio 
@Component
public interface ProductoService {
	public List<Producto> buscarPorIds(List<Id> codigos);

	public List<Producto> buscarTodos();

	public void guardar(Producto producto);

	public Producto buscarPorId(String id);

	public void eliminarPorId(String id);

	public void editar(Producto producto);
}
