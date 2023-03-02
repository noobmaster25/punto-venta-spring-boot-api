package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CategoriaDTO;
import com.example.demo.DTO.CategoriaNuevaDTO;
import com.example.demo.service.CategoriaService;

@RestController
@RequestMapping("categorias")
@CrossOrigin
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaServi;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> obtenerTodos() {
		return ResponseEntity.ok(categoriaServi.obtenerTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> obtenerPorId(@PathVariable int id) throws Exception {
		return new ResponseEntity<>(categoriaServi.obtenerPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CategoriaDTO> crearCategoria(@RequestBody CategoriaNuevaDTO categoriaDto) {
		CategoriaDTO categoriaGuardada = categoriaServi.guardarCategoria(categoriaDto);
		return new ResponseEntity<>(categoriaGuardada, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable int id) {
		categoriaServi.eliminarCategoria(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDTO> actualizarCategoria(@PathVariable int id,
			@RequestBody CategoriaNuevaDTO categoriaDto) throws Exception {
		return ResponseEntity.ok(categoriaServi.actualizarCategoria(id, categoriaDto));
	}

}