package com.example.demo.infraestructura.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dominio.model.Factura;
import com.example.demo.infraestructura.dto.FacturaDto;
import com.example.demo.infraestructura.dto.FacturaRest;
import com.example.demo.shared.dominio.Cliente;
import com.example.demo.shared.dominio.Id;
import com.example.demo.shared.dominio.Telefono;
import com.example.demo.shared.dominio.Total;
import com.example.demo.shared.infraestructure.mapper.MapperApi;
import com.example.demo.shared.infraestructure.mapper.MapperRepository;

@Component
public class FacturaMapper implements MapperApi<FacturaDto, Factura>, MapperRepository<FacturaRest, Factura> {

	@Autowired
	ItemMapper itemMaper;

	@Override
	public FacturaDto apitransformarDominioParaDto(Factura o) {

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
	public Factura apitransformarDtoParaDominio(FacturaDto i) {

		return Factura.of(new Id(i.getId()), new Total(i.getTotal()), new Cliente(i.getCliente()),
				new Telefono(i.getTelefono()), i.getItem().stream()
						.map(item -> itemMaper.transformarDtoParaDominio(item)).collect(Collectors.toList()));

	}

	@Override
	public FacturaRest transformarDominioParaDto(Factura o) {

		FacturaRest factura = new FacturaRest();

		factura.setId(o.getId().getValue());
		factura.setCliente(o.getCliente().getNombre());
		factura.setItem(itemMaper.apitransformarListDominioParaDto(o.getItem()));
		factura.setTelefono(o.getTelefono().getTelefono());
		factura.setTotal(o.getTotal().getTotal());

		return factura;
	}

	@Override
	public Factura transformarDtoParaDominio(FacturaRest i) {
		return Factura.of(new Id(i.getId()), new Total(i.getTotal()), new Cliente(i.getCliente()),
				new Telefono(i.getTelefono()), itemMaper.apitransformarListaDtoParaDominio(i.getItem()));
	}

}
