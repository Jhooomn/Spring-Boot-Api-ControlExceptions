package com.example.demo.infraestructura.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dominio.model.Factura;
import com.example.demo.dominio.model.Item;
import com.example.demo.infraestructura.dto.FacturaDto;
import com.example.demo.shared.dominio.Cliente;
import com.example.demo.shared.dominio.Id;
import com.example.demo.shared.dominio.Telefono;
import com.example.demo.shared.dominio.Total;
import com.example.demo.shared.infraestructure.mapper.MapperApiRest;

public class FacturaMapper implements MapperApiRest<FacturaDto, Factura> {

	@Autowired
	ItemMapper itemMaper;

	@Override
	public FacturaDto transformarDominioParaDto(Factura o) {

		FacturaDto factura = new FacturaDto();

		factura.setId(o.getId().getValue());
		factura.setCliente(o.getCliente().getNombre());
		factura.setItem(o.getItem().stream().map(item -> itemMaper.transformarDominioParaDto(item))
				.collect(Collectors.toList()));
		factura.setTelefono(o.getTelefono().getTelefono());
		factura.setTotal(o.getTotal().getTotal());

		return factura;
	}

	@Override
	public Factura transformarDtoParaDominio(FacturaDto i) {

		Factura.of(new Id(i.getId()), new Total(i.getTotal()), new Cliente(i.getCliente()),
				new Telefono(i.getTelefono()), i.getItem().stream()
						.map(item -> itemMaper.transformarDtoParaDominio(item)).collect(Collectors.toList()));

		return null;
	}

}
