package com.example.demo.aplicacion;

import java.util.ArrayList;
import java.util.List;
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
		// Obtener todos los codigos de los items
		List<String> codigos = cargarCodigos(factura.getItem());
		// Obtener todos los productos
		List<ProductoRest> productos = cargarProductos(codigos);
		// Obtener la información completa de los items
		cargarItems(productos, factura.getItem());
		// Obtener el valor de la Factura
		Double total = calcularValorFactura(factura);
		factura.setTotal(total);
		facturaService.guardar(facturaMapper.transformarDtoParaDominio(factura));
	}

	public List<String> cargarCodigos(List<ItemRest> items) {
		List<String> codigos = new ArrayList<>();
		for (ItemRest item : items) {
			codigos.add(item.getProducto().getId());
		}
		return codigos;
	}

	public List<ItemRest> cargarItems(List<ProductoRest> productos, List<ItemRest> factura_items) {
		List<ItemRest> items = new ArrayList<>();

		for (ProductoRest p : productos) {
			for (ItemRest i : factura_items) {
				if (i.getProducto().getId().equalsIgnoreCase(p.getId())) {
					i.setProducto(p);
					i.setTotal(i.getCantidad() * p.getValor());
					items.add(i);
				}
			}
		}
		return items;
	}

	public List<ProductoRest> cargarProductos(List<String> codigo) {
		return productoMapper.apitransformarListDominioParaDto(productoService.buscarTodos());
	}

	public Double calcularValorFactura(FacturaRest factura) {
		Double total = 0.0;
		for (ItemRest item : factura.getItem()) {
			total = total + item.getTotal();
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
