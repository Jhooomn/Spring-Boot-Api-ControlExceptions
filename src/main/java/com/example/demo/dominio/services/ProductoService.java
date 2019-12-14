package com.example.demo.dominio.services;

import java.util.List;

import com.example.demo.dominio.model.Producto;
import com.example.demo.shared.dominio.Id;

// Productos de dominio 
public interface ProductoService {
	public List<Producto> buscarPorIds(List<Id> codigos);

	public List<Producto> buscarTodos();

	public void guardar(Producto producto);

	public Producto buscarPorId(String id);
}
