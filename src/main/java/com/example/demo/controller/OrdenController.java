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

import com.example.demo.DTO.CrearOrdenDTO;
import com.example.demo.DTO.OrdenDTO;
import com.example.demo.entities.Orden;
import com.example.demo.service.OrdenService;

@RestController
@RequestMapping("ordenes")
@CrossOrigin
public class OrdenController {

	@Autowired
	private OrdenService ordenServi;
	
	@GetMapping
	public ResponseEntity<List<OrdenDTO>> obtenerTodos(){
		return ResponseEntity.ok(ordenServi.obtenerTodos());
	}
	@GetMapping("/{id}")
	public ResponseEntity<OrdenDTO> obterPorId(@PathVariable Integer id) throws Exception{
		return ResponseEntity.ok(ordenServi.obtenerPorId(id));
	}
	@PostMapping
	public ResponseEntity<OrdenDTO> guardarOrden(@RequestBody CrearOrdenDTO orden) throws Exception{
		System.out.println(orden);
		return new ResponseEntity<>(ordenServi.guardarOrden(orden), HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Orden> actualizarOrden(@PathVariable Integer id, @RequestBody Orden orden) throws Exception{
		return ResponseEntity.ok(ordenServi.actualizarOrden(id, orden));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id) throws Exception{
		ordenServi.eliminarPorId(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
