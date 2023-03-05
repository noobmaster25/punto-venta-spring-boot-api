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

import com.example.demo.DTO.OrdenDTO;
import com.example.demo.DTO.OrdenNuevaDTO;
import com.example.demo.service.OrdenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("ordenes")
@CrossOrigin
public class OrdenController {

	@Autowired
	private OrdenService ordenServi;

	@GetMapping
	public ResponseEntity<Page<OrdenDTO>> obtenerTodos(@RequestParam(defaultValue = "0") int noPagina,
														@RequestParam(defaultValue = "10") int tamanioPagina) {
		return ResponseEntity.ok(ordenServi.obtenerTodos(noPagina,tamanioPagina));
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrdenDTO> obterPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(ordenServi.obtenerPorId(id));
	}

	@PostMapping
	public ResponseEntity<OrdenDTO> guardarOrden(@Valid @RequestBody OrdenNuevaDTO ordenDto) {
		return new ResponseEntity<>(ordenServi.guardarOrden(ordenDto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrdenDTO> actualizarOrden(@PathVariable Integer id,
			@Valid @RequestBody OrdenNuevaDTO ordenDto) {
		return ResponseEntity.ok(ordenServi.actualizarOrden(id, ordenDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id) {
		ordenServi.eliminarPorId(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
