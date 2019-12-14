package com.example.demo.infraestructura.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.infraestructura.dto.ProductoDto;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoDto, String> {

	public ProductoDto findByNombre(String nombre);
	public ProductoDto findByValor(Double valor);
	
}
