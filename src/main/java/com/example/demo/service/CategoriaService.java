package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CategoriaDTO;
import com.example.demo.DTO.CategoriaNuevaDTO;
import com.example.demo.entities.Categoria;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.ICategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private ICategoriaRepository categoriaRepo;

	public Page<CategoriaDTO> obtenerTodos(int noPagina, int tamanioPagina) {
		Pageable pageable = PageRequest.of(noPagina, tamanioPagina);
		Page<Categoria> categorias = categoriaRepo.findAll(pageable);
		List<CategoriaDTO> categoriasDto = categorias.getContent().stream().map(c -> new CategoriaDTO(c))
				.collect(Collectors.toList());
		return new PageImpl<>(categoriasDto, pageable, categorias.getTotalElements());
	}

	public CategoriaDTO obtenerPorId(int id) {
		Optional<Categoria> categoria = categoriaRepo.findById(id);
		if (categoria.isEmpty()) {
			throw new NotFoundException("no se encontro categoria con id :" + id);
		}
		return new CategoriaDTO(categoria.get());
	}

	public CategoriaDTO guardarCategoria(CategoriaNuevaDTO categoriaDto) {
		Categoria categoriaNueva = new Categoria(categoriaDto.getNombre(), categoriaDto.getDescripcion());
		categoriaRepo.save(categoriaNueva);
		return new CategoriaDTO(categoriaNueva);
	}

	public void eliminarCategoria(int id) {
		categoriaRepo.deleteById(id);
	}

	public CategoriaDTO actualizarCategoria(int id, CategoriaNuevaDTO categoriaDto) {
		Optional<Categoria> categoriaAnterior = categoriaRepo.findById(id);

		if (categoriaAnterior.isEmpty()) {
			throw new NotFoundException("no se encontro categoria con id :" + id);
		}
		Categoria categoriaActualizada = categoriaAnterior.get();
		categoriaActualizada.setNombre(categoriaDto.getNombre());
		categoriaActualizada.setDescripcion(categoriaDto.getDescripcion());

		categoriaRepo.save(categoriaActualizada);

		return new CategoriaDTO(categoriaActualizada);

	}

}
