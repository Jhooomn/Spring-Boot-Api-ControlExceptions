package com.example.demo.aplicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dominio.model.Factura;
import com.example.demo.dominio.services.FacturaService;
import com.example.demo.dominio.services.ProductoService;
import com.example.demo.exceptions.EditadoHandlerException;
import com.example.demo.exceptions.RegistroNoEncontradoException;
import com.example.demo.infraestructura.dto.FacturaDto;
import com.example.demo.infraestructura.dto.FacturaRest;
import com.example.demo.infraestructura.dto.ItemDto;
import com.example.demo.infraestructura.dto.ItemRest;
import com.example.demo.infraestructura.dto.ProductoDto;
import com.example.demo.infraestructura.dto.ProductoRest;
import com.example.demo.infraestructura.mapper.FacturaMapper;
import com.example.demo.infraestructura.mapper.ProductoMapper;
import com.example.demo.infraestructura.repository.database.FacturaRepository;
import com.example.demo.infraestructura.repository.database.ProductoRepository;

@Component
public class FacturaAplicacion {

	@Autowired
	FacturaRepository facturaRepository;

	//
	@Autowired
	FacturaService facturaService;
	@Autowired
	FacturaMapper facturaMapper;
	@Autowired
	ProductoService productoService;
	@Autowired
	ProductoMapper productoMapper;

	//
	public List<FacturaRest> getFacturas() {
		return facturaMapper.transformarListDominioParaDto(facturaService.buscarTodos());
	}

	public FacturaRest getFactura(String id) {
		return facturaMapper.transformarDominioParaDto(facturaService.buscarPorId(id));
	}

	// ***********************************************************************************************

	public void addFactura(FacturaRest factura) {
//		factura.setItem(this.getITemId(factura.getItem()));

		FacturaRest fa = factura;

		fa.setItem(this.cargarItems(fa.getItem()));
		fa.setTotal(this.calcularFactura(fa.getItem()));

		facturaService.guardar(facturaMapper.transformarDtoParaDominio(fa));
	}

	public List<ItemRest> cargarItems(List<ItemRest> items) {

		List<ItemRest> item = new ArrayList<ItemRest>();

		for (ItemRest i : items) {
			if (i.getProducto().getId().equalsIgnoreCase(i.getId())) {
				i.setTotal(i.getCantidad() * i.getProducto().getValor());
				item.add(i);
			}
		}

		return item;
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
