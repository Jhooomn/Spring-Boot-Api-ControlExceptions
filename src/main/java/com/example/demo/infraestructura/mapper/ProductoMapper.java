package com.example.demo.infraestructura.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dominio.model.Producto;
import com.example.demo.infraestructura.dto.ProductoDto;
import com.example.demo.infraestructura.dto.ProductoRest;
import com.example.demo.shared.dominio.Id;
import com.example.demo.shared.dominio.Nombre;
import com.example.demo.shared.dominio.Valor;
import com.example.demo.shared.infraestructure.mapper.MapperApi;
import com.example.demo.shared.infraestructure.mapper.MapperRepository;

@Component
public class ProductoMapper implements MapperRepository<ProductoDto, Producto>, MapperApi<ProductoRest, Producto> {

	@Override
	public ProductoRest apitransformarDominioParaDto(Producto o) {
		// TODO Auto-generated method stub
		ProductoRest producto = new ProductoRest();

		producto.setId(o.getId().getValue());
		producto.setNombre(o.getNombre().getValue());
		producto.setValor(o.getValor().getValue());

		return producto;
	}

	@Override
	public Producto apitransformarDtoParaDominio(ProductoRest i) {
		// TODO Auto-generated method stub

		return Producto.of(new Id(i.getId()), new Nombre(i.getNombre()), new Valor(i.getValor()));
	}

	@Override
	public ProductoDto transformarDominioParaDto(Producto o) {

		ProductoDto producto = new ProductoDto();

		producto.setId(o.getId().getValue());
		producto.setNombre(o.getNombre().getValue());
		producto.setValor(o.getValor().getValue());

		return producto;
	}

	@Override
	public Producto transformarDtoParaDominio(ProductoDto i) {
		// TODO Auto-generated method stub
		return Producto.of(new Id(i.getId()), new Nombre(i.getNombre()), new Valor(i.getValor()));

	}

}
