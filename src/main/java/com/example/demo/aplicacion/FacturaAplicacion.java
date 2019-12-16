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

import com.example.demo.dominio.services.FacturaService;
import com.example.demo.exceptions.EditadoHandlerException;
import com.example.demo.exceptions.RegistroNoEncontradoException;
import com.example.demo.infraestructura.dto.FacturaDto;
import com.example.demo.infraestructura.dto.ItemDto;
import com.example.demo.infraestructura.dto.ProductoDto;
import com.example.demo.infraestructura.mapper.FacturaMapper;
import com.example.demo.infraestructura.repository.database.FacturaRepository;
import com.example.demo.infraestructura.repository.database.ProductoRepository;

@Component
public class FacturaAplicacion {

	@Autowired
	FacturaRepository facturaRepository;

	@Autowired
	ProductoRepository productoRepository;

	// 
	@Autowired
	FacturaService facturaService;
	@Autowired
	FacturaMapper facturaMapper;
	
	@Autowired
	
	//
	@GetMapping()
	public List<FacturaDto> getFacturas() {
//		return facturaRepository.findAll();
		return facturaService.buscarTodos().stream().map(factura -> facturaMapper.apitransformarDominioParaDto(factura))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public FacturaDto getFactura(@PathVariable String id) {
//		return facturaRepository.findById(id).orElseThrow(() -> new RegistroNoEncontradoException());
		return facturaMapper.apitransformarDominioParaDto(facturaService.buscarPorId(id));
	}

	// ***********************************************************************************************

	@PostMapping()
	public void addFactura(@RequestBody FacturaDto factura) {
		// Obtener todos los codigos de los items
		List<String> codigos = cargarCodigos(factura.getItem());
		// Obtener todos los productos
		List<ProductoDto> productos = cargarProductos(codigos);
		// Obtener la información completa de los items
		cargarItems(productos, factura.getItem());
		// Obtener el valor de la Factura
		Double total = calcularValorFactura(factura);
		factura.setTotal(total);
		facturaRepository.save(factura);
	}

	public List<String> cargarCodigos(List<ItemDto> items) {
		List<String> codigos = new ArrayList<>();
		for (ItemDto item : items) {
			codigos.add(item.getProducto().getId());
		}
		return codigos;
	}

	public List<ItemDto> cargarItems(List<ProductoDto> productos, List<ItemDto> factura_items) {
		List<ItemDto> items = new ArrayList<>();

		for (ProductoDto p : productos) {
			for (ItemDto i : factura_items) {
				if (i.getProducto().getId().equalsIgnoreCase(p.getId())) {
					i.setProducto(p);
					i.setTotal(i.getCantidad() * p.getValor());
					items.add(i);
				}
			}
		}
		return items;
	}

	public List<ProductoDto> cargarProductos(List<String> codigo) {

		return productoRepository.findAllById(codigo);

	}

	public Double calcularValorFactura(FacturaDto factura) {
		Double total = 0.0;
		for (ItemDto item : factura.getItem()) {
			total = total + item.getTotal();
		}
		System.out.println(total);
		return total;
	}

	// ***********************************************************************************************

	@PutMapping()
	public void editFactura(@RequestBody FacturaDto factura) {
		facturaRepository.findById(factura.getId()).orElseThrow(() -> new EditadoHandlerException());
		facturaRepository.save(factura);
	}

	@DeleteMapping("/{id}")
	public void deleteFactura(@PathVariable String id) {
//		facturaRepository.findById(id).orElseThrow(() -> new RegistroNoEncontradoException());
//		facturaRepository.deleteById(id);
		facturaService.eliminarPorId(id);
	}

	@DeleteMapping
	public void deleteAll() {
		facturaRepository.deleteAll();
	}
}
