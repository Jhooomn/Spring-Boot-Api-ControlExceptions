package com.example.demo.shared.infraestructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface MapperApiRest<I, O> {

	// Transformar
	public I transformarDominioParaDto(O o);

	public O transformarDtoParaDominio(I i);

	public default List<O> transformarListaDtoParaDominio(List<I> instancias) {
		return instancias.stream().map(o -> transformarDtoParaDominio(o)).collect(Collectors.toList());
	}

	public default List<I> transformarListDominioParaDto(List<O> instancias) {
		return instancias.stream().map(i -> transformarDominioParaDto(i)).collect(Collectors.toList());
	}
}
