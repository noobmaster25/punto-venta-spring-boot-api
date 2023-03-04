package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CategoriaDTO;
import com.example.demo.DTO.CategoriaNuevaDTO;
import com.example.demo.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("categorias")
@CrossOrigin
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaServi;

	@GetMapping
	public ResponseEntity<Page<CategoriaDTO>> obtenerTodos(@RequestParam(defaultValue = "0") int noPagina,
															@RequestParam(defaultValue = "10") int tamanioPagina) {
		return ResponseEntity.ok(categoriaServi.obtenerTodos(noPagina,tamanioPagina));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> obtenerPorId(@PathVariable int id) {
		return new ResponseEntity<>(categoriaServi.obtenerPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CategoriaDTO> crearCategoria(@Valid @RequestBody CategoriaNuevaDTO categoriaDto) {
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
			@Valid @RequestBody CategoriaNuevaDTO categoriaDto) {
		return ResponseEntity.ok(categoriaServi.actualizarCategoria(id, categoriaDto));
	}

}