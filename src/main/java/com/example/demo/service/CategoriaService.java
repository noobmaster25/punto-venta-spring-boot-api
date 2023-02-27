package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Categoria;
import com.example.demo.repository.ICategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private ICategoriaRepository categoriaRepo;

	public List<Categoria> obtenerTodos() {
		return categoriaRepo.findAll();
	}

	public Categoria obtenerPorId(int id) throws Exception {
		Optional<Categoria> categoria = categoriaRepo.findById(id);
		if (categoria.isEmpty()) {
			throw new Exception("no encontrado");
		}
		return categoria.get();
	}

	public Categoria guardarCategoria(Categoria categoria) {

		return categoriaRepo.save(categoria);
	}

	public void eliminarCategoria(int id) {
		categoriaRepo.deleteById(id);
	}

	public Categoria actualizarCategoria(int id, Categoria categoria) throws Exception {
		Optional<Categoria> categoriaAnterior = categoriaRepo.findById(id);

		if (categoriaAnterior.isEmpty()) {
			throw new Exception("no se encontro la categoria");
		}
		System.out.println(categoriaAnterior.get());
		Categoria categoriaActualizada = categoriaAnterior.get();
		categoriaActualizada.setNombre(categoria.getNombre());
		categoriaActualizada.setDescripcion(categoria.getDescripcion());

		return categoriaRepo.save(categoriaActualizada);

	}

}
