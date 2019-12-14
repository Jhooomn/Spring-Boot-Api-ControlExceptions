package com.example.demo.infraestructura.repository.adapters;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dominio.model.Producto;
import com.example.demo.dominio.services.ProductoService;
import com.example.demo.shared.dominio.Id;

@Service
public class ProductoAdapters implements ProductoService {

	
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
