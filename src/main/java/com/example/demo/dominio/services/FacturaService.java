package com.example.demo.dominio.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dominio.model.Factura;
import com.example.demo.shared.dominio.Id;

@Component
public interface FacturaService {

	public List<Factura> buscarPorIds(List<Id> codigos);

	public List<Factura> buscarTodos();

	public void guardar(Factura factura);

	public Factura buscarPorId(String id);

	public void eliminarPorId(String id);

	public void editar(Factura factura);

}
