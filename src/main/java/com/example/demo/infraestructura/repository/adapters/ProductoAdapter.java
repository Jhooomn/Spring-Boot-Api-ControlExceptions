package com.example.demo.infraestructura.repository.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dominio.model.Producto;
import com.example.demo.dominio.services.ProductoService;
import com.example.demo.infraestructura.repository.database.ProductoRepository;
import com.example.demo.shared.dominio.Id;

public class ProductoAdapter implements ProductoService {

	@Autowired
	ProductoRepository productoRepository;

	@Override
	public List<Producto> buscarPorIds(List<Id> codigos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(Producto producto) {
		// TODO Auto-generated method stub

	}

	@Override
	public Producto buscarPorId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
