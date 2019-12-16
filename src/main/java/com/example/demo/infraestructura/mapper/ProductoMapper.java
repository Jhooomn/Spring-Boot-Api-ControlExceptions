package com.example.demo.infraestructura.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dominio.model.Producto;
import com.example.demo.infraestructura.dto.ProductoDto;
import com.example.demo.shared.dominio.Id;
import com.example.demo.shared.dominio.Nombre;
import com.example.demo.shared.dominio.Valor;
import com.example.demo.shared.infraestructure.mapper.MapperApi;

@Component
public class ProductoMapper implements MapperApi<ProductoDto, Producto> {

	@Override
	public ProductoDto apitransformarDominioParaDto(Producto o) {
		ProductoDto producto = new ProductoDto();

		producto.setId(o.getId().getValue());
		producto.setNombre(o.getNombre().getValue());
		producto.setValor(o.getValor().getValue());

		return producto;
	}

	@Override
	public Producto apitransformarDtoParaDominio(ProductoDto i) {
		return Producto.of(new Id(i.getId()), new Nombre(i.getNombre()), new Valor(i.getValor()));
	}

}
