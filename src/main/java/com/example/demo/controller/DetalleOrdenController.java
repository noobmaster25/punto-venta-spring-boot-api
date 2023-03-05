package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.DetalleOrdenDTO;
import com.example.demo.DTO.DetalleOrdenNuevaDTO;
import com.example.demo.service.DetalleOrdenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("detalles_orden")
public class DetalleOrdenController {

	@Autowired
	private DetalleOrdenService detalleServi;

	@GetMapping
	public ResponseEntity<Page<DetalleOrdenDTO>> obtenerTodos(@RequestParam(defaultValue = "0") int noPagina,
															@RequestParam(defaultValue = "10") int tamanioPagina) {
		return ResponseEntity.ok(detalleServi.obtenerTodos(noPagina,tamanioPagina));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalleOrdenDTO> obtenerPorId(@PathVariable Integer id){
		return ResponseEntity.ok(detalleServi.obtenerPorId(id));
	}

	@PostMapping
	public ResponseEntity<DetalleOrdenDTO> guardarDetalleOrden(@Valid @RequestBody DetalleOrdenNuevaDTO detalleOrdenDto)
			throws Exception {
		return new ResponseEntity<>(detalleServi.guardarDetalle(detalleOrdenDto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DetalleOrdenDTO> actualizarPorId(@PathVariable Integer id,
			@Valid @RequestBody DetalleOrdenNuevaDTO detalleOrdenDto){
		return ResponseEntity.ok(detalleServi.actualizarDetalle(id, detalleOrdenDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id){
		detalleServi.eliminarDetalle(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
