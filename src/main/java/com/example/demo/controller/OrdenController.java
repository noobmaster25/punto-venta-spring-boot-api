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
	public ResponseEntity<List<OrdenDTO>> obtenerTodos(){
		return ResponseEntity.ok(ordenServi.obtenerTodos());
	}
	@GetMapping("/{id}")
	public ResponseEntity<OrdenDTO> obterPorId(@PathVariable Integer id) throws Exception{
		return ResponseEntity.ok(ordenServi.obtenerPorId(id));
	}
	@PostMapping
	public ResponseEntity<OrdenDTO> guardarOrden(@Valid @RequestBody OrdenNuevaDTO ordenDto) throws Exception{
		return new ResponseEntity<>(ordenServi.guardarOrden(ordenDto), HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<OrdenDTO> actualizarOrden(@PathVariable Integer id, @Valid @RequestBody OrdenNuevaDTO ordenDto) throws Exception{
		return ResponseEntity.ok(ordenServi.actualizarOrden(id, ordenDto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id) throws Exception{
		ordenServi.eliminarPorId(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
