package com.example.demo.infraestructura.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dominio.model.Item;
import com.example.demo.dominio.model.Producto;
import com.example.demo.infraestructura.dto.ItemDto;
import com.example.demo.infraestructura.dto.ItemRest;
import com.example.demo.infraestructura.dto.ProductoDto;
import com.example.demo.infraestructura.dto.ProductoRest;
import com.example.demo.shared.dominio.Cantidad;
import com.example.demo.shared.dominio.Cliente;
import com.example.demo.shared.dominio.Id;
import com.example.demo.shared.dominio.Nombre;
import com.example.demo.shared.dominio.Total;
import com.example.demo.shared.dominio.Valor;
import com.example.demo.shared.infraestructure.mapper.MapperApi;
import com.example.demo.shared.infraestructure.mapper.MapperRepository;

@Component
public class ItemMapper implements MapperRepository<ItemDto, Item>, MapperApi<ItemRest, Item> {

	@Override
	public ItemRest apitransformarDominioParaDto(Item o) {

		ItemRest item = new ItemRest();

		item.setId(o.getId().getValue());
		item.setCantidad(o.getCantidad().getValue());

		item.setProducto(new ProductoRest(o.getProducto().getId().getValue(), o.getProducto().getNombre().getValue(),
				o.getProducto().getValor().getValue()));

		item.setTotal(o.getTotal().getTotal());

		return item;
	}

	@Override
	public Item apitransformarDtoParaDominio(ItemRest i) {
		return Item.of(new Id(i.getId()), new Cantidad(i.getCantidad()), new Total(i.getTotal()),
				Producto.of(new Id(i.getProducto().getId()), new Nombre(i.getProducto().getNombre()),
						new Valor(i.getProducto().getValor())));
	}

	@Override
	public ItemDto transformarDominioParaDto(Item o) {

		ItemDto item = new ItemDto();

		ProductoDto producto = new ProductoDto();

		producto.setId(o.getProducto().getId().getValue());
		producto.setNombre(o.getProducto().getNombre().getValue());
		producto.setValor(o.getProducto().getValor().getValue());

		item.setId(o.getId().getValue());
		item.setCantidad(o.getCantidad().getValue());
		item.setProducto(producto);
		item.setTotal(o.getTotal().getTotal());

		return item;
	}

	@Override
	public Item transformarDtoParaDominio(ItemDto i) {
		return Item.of(new Id(i.getId()), new Cantidad(i.getCantidad()), new Total(i.getTotal()),
				Producto.of(new Id(i.getProducto().getId()), new Nombre(i.getProducto().getNombre()),
						new Valor(i.getProducto().getValor())));

	}

}
