package com.example.demo.aplicacion;

import java.util.List;
import java.util.UUID;
import com.example.demo.dominio.services.FacturaService;
import com.example.demo.dominio.services.ProductoService;
import com.example.demo.infraestructura.dto.FacturaRest;
import com.example.demo.infraestructura.dto.ItemRest;
import com.example.demo.infraestructura.dto.ProductoRest;
import com.example.demo.infraestructura.mapper.FacturaMapper;
import com.example.demo.infraestructura.mapper.ProductoMapper;
import com.example.demo.infraestructura.repository.database.FacturaRepository;

public class FacturaAplicacion {

	FacturaRepository facturaRepository;
	FacturaService facturaService;
	FacturaMapper facturaMapper;
	ProductoService productoService;
	ProductoMapper productoMapper;

	public FacturaAplicacion(FacturaRepository facturaRepository, FacturaService facturaService,
			FacturaMapper facturaMapper, ProductoService productoService, ProductoMapper productoMapper) {
		this.facturaRepository = facturaRepository;
		this.facturaService = facturaService;
		this.facturaMapper = facturaMapper;
		this.productoService = productoService;
		this.productoMapper = productoMapper;
	}

	//
	public List<FacturaRest> getFacturas() {
		return facturaMapper.transformarListDominioParaDto(facturaService.buscarTodos());
	}

	public FacturaRest getFactura(String id) {
		return facturaMapper.transformarDominioParaDto(facturaService.buscarPorId(id));
	}

	// ***********************************************************************************************

	public void addFactura(FacturaRest factura) {
		FacturaRest fa = factura;
		fa.setId(UUID.randomUUID().toString());
		fa.setItem(this.cargarItems(fa.getItem()));
		fa.setTotal(this.calcularFactura(fa.getItem()));

		facturaService.guardar(facturaMapper.transformarDtoParaDominio(fa));
	}

	public List<ItemRest> cargarItems(List<ItemRest> items) {
		for (ItemRest i : items) {
			i.setId(UUID.randomUUID().toString());
			i.setProducto(this.cargarProducto(i.getProducto()));
			i.setTotal(i.getCantidad() * i.getProducto().getValor());
		}

		return items;
	}

	public ProductoRest cargarProducto(ProductoRest producto) {
		return productoMapper.apitransformarDominioParaDto(productoService.buscarPorId(producto.getId()));
	}

	public Double calcularFactura(List<ItemRest> item) {
		double total = 0.0;
		for (ItemRest i : item) {
			total = total + (i.getCantidad() * i.getProducto().getValor());
		}
		return total;
	}

	// ***********************************************************************************************

	public void editFactura(FacturaRest factura) {
		facturaService.buscarPorId(factura.getId());
		facturaService.editar(facturaMapper.transformarDtoParaDominio(factura));
	}

	public void deleteFactura(String id) {
		facturaService.eliminarPorId(id);
	}

	// Solo para pruebas, no es utilizado en producción
	public void deleteAll() {
		facturaRepository.deleteAll();
	}
}
