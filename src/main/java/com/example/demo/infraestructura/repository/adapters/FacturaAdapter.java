package com.example.demo.infraestructura.repository.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dominio.model.Factura;
import com.example.demo.dominio.services.FacturaService;
import com.example.demo.infraestructura.dto.FacturaDto;
import com.example.demo.infraestructura.mapper.FacturaMapper;
import com.example.demo.infraestructura.repository.database.FacturaRepository;
import com.example.demo.shared.dominio.Id;

@Service
public class FacturaAdapter implements FacturaService {

	@Autowired
	FacturaRepository facturaRepository;

	@Autowired
	FacturaMapper facturaMapper;

	@Override
	public List<Factura> buscarPorIds(List<Id> codigos) {

		return facturaRepository
				.findAllById(codigos.stream().map(codigo -> codigo.getValue()).collect(Collectors.toList())).stream()
				.map(factura -> facturaMapper.apitransformarDtoParaDominio(factura)).collect(Collectors.toList());
	}

	@Override
	public List<Factura> buscarTodos() {
		return facturaMapper.apitransformarListaDtoParaDominio(facturaRepository.findAll());
	}

	@Override
	public void guardar(Factura factura) {
		facturaRepository.save(facturaMapper.apitransformarDominioParaDto(factura));
	}

	@Override
	public Factura buscarPorId(String id) {
		return facturaMapper.apitransformarDtoParaDominio(facturaRepository.findById(id).get());
	}

	@Override
	public void eliminarPorId(String id) {
		FacturaDto factura = facturaRepository.findById(id).get();
		facturaRepository.deleteById(factura.getId());
	}

	@Override
	public void editar(Factura factura) {
		this.buscarPorId(factura.getId().getValue());
		facturaRepository.save(facturaMapper.apitransformarDominioParaDto(factura));

	}

}
