package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

	public Producto findByNombre(String nombre);
	public Producto findByValor(Double valor);
	
}
