package com.example.demo.shared.infraestructure.mapper;

public interface MapperApiRest<I, O> {

	// Transformar
	public I transformarDominioParaDto(O o);

	public O transformarDtoParaDominio(I i);
}
