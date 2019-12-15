package com.example.demo.infraestructura.mapper;

import com.example.demo.dominio.model.Item;
import com.example.demo.dominio.model.Producto;
import com.example.demo.infraestructura.dto.ItemDto;
import com.example.demo.infraestructura.dto.ProductoDto;
import com.example.demo.shared.dominio.Cantidad;
import com.example.demo.shared.dominio.Cliente;
import com.example.demo.shared.dominio.Id;
import com.example.demo.shared.dominio.Nombre;
import com.example.demo.shared.dominio.Total;
import com.example.demo.shared.dominio.Valor;
import com.example.demo.shared.infraestructure.mapper.MapperApiRest;

public class ItemMapper implements MapperApiRest<ItemDto, Item> {

	@Override
	public ItemDto transformarDominioParaDto(Item o) {

		ItemDto item = new ItemDto();
		ProductoDto producto = new ProductoDto();

		producto.setId(o.getProducto().getId().getValue());
		producto.setNombre(o.getProducto().getNombre().getValue());
		producto.setValor(o.getProducto().getValor().getValue());

		item.setCantidad(o.getCantidad().getValue());
		item.setId(o.getId().getValue());
		item.setProducto(producto);
		item.setTotal(o.getTotal().getTotal());

		return item;
	}

	@Override
	public Item transformarDtoParaDominio(ItemDto i) {
		return Item.of(new Id(i.getId()), new Cantidad(i.getCantidad()), new Total(i.getTotal()), Producto
				.of(new Id(i.getId()), new Nombre(i.getProducto().getNombre()), new Valor(i.getProducto().getValor())));
	}

}
