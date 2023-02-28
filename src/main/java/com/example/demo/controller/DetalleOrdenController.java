package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.DetalleOrdenDTO;
import com.example.demo.entities.DetalleOrden;
import com.example.demo.service.DetalleOrdenService;

@RestController
@RequestMapping("detalles_orden")
public class DetalleOrdenController {

	@Autowired
	private DetalleOrdenService detalleServi;
	
	@GetMapping
	public ResponseEntity<List<DetalleOrdenDTO>> obtenerTodos(){
		return ResponseEntity.ok(detalleServi.obtenerTodos());
	}
	@GetMapping("/{id}")
	public ResponseEntity<DetalleOrden> obtenerPorId(@PathVariable Integer id) throws Exception{
		return ResponseEntity.ok(detalleServi.obtenerPorId(id));
	}
	@PostMapping
	public ResponseEntity<DetalleOrden> guardarDetalleOrden(@RequestBody DetalleOrden detalleOrden){
		return new ResponseEntity<>(detalleServi.guardarDetalle(detalleOrden),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DetalleOrden> actualizarPorId(@PathVariable Integer id, @RequestBody DetalleOrden detalleOrden) throws Exception{
		return ResponseEntity.ok(detalleServi.actualizarDetalle(id, detalleOrden));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id) throws Exception{
		detalleServi.eliminarDetalle(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
		
}