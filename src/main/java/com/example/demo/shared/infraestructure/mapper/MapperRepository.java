package com.example.demo.shared.infraestructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface MapperRepository<I, O> {

	public I transformarDominioParaDto(O o);//

	public O transformarDtoParaDominio(I i);

	public default List<O> transformarListaDtoParaDominio(List<I> instancias) {
//		return instancias.stream().map(this::transformarDtoParaDominio).collect(Collectors.toList());	
		// instancias.stream().forEach(System.out::println);
		return instancias.stream().map(i -> transformarDtoParaDominio(i)).collect(Collectors.toList());
	}

	public default List<I> transformarListDominioParaDto(List<O> instancias) {
		return instancias.stream().map(o -> transformarDominioParaDto(o)).collect(Collectors.toList());
	}

}
