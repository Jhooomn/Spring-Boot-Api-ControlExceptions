package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ProductoDto;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoDto, String> {

	public ProductoDto findByNombre(String nombre);
	public ProductoDto findByValor(Double valor);
	
}
