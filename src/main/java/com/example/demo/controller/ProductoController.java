package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.demo.DTO.ProductoDTO;
import com.example.demo.DTO.ProductoNuevoDTO;
import com.example.demo.entities.Producto;
import com.example.demo.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("productos")
@CrossOrigin
public class ProductoController {
	@Autowired
	private ProductoService productoServi;
	
	@GetMapping
	public ResponseEntity<List<ProductoDTO>> obtenerTodos() {
		return ResponseEntity.ok(productoServi.obtenerTodos());
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProductoDTO> obtenPorId(@PathVariable int id) throws Exception{		
		return new ResponseEntity<>(productoServi.obtenerPorId(id),HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoNuevoDTO productoDto) throws Exception {
		ProductoDTO productoCreadoDto = productoServi.crearProducto(productoDto);		
		return new ResponseEntity<>(productoCreadoDto, HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public ResponseEntity<Producto> actualizarProducto(@PathVariable int id , @RequestBody Producto producto) throws Exception{
		Producto productoActualizado = productoServi.actualizarProducto(id, producto);
		return ResponseEntity.ok(productoActualizado);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable int id) {
		productoServi.eliminarProducto(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}