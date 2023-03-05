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

import com.example.demo.DTO.ProductoDTO;
import com.example.demo.DTO.ProductoNuevoDTO;
import com.example.demo.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("productos")
@CrossOrigin
public class ProductoController {
	@Autowired
	private ProductoService productoServi;

	@GetMapping
	public ResponseEntity<Page<ProductoDTO>> obtenerTodos(@RequestParam(defaultValue = "0") int noPagina,
															@RequestParam(defaultValue = "10") int tamanioPagina) {
		return ResponseEntity.ok(productoServi.obtenerTodos(noPagina,tamanioPagina));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductoDTO> obtenPorId(@PathVariable int id) {
		return new ResponseEntity<>(productoServi.obtenerPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoNuevoDTO productoDto)
			throws Exception {
		ProductoDTO productoCreadoDto = productoServi.crearProducto(productoDto);
		return new ResponseEntity<>(productoCreadoDto, HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable int id,
			@Valid @RequestBody ProductoNuevoDTO productoDto) {
		ProductoDTO productoActualizadoDto = productoServi.actualizarProducto(id, productoDto);
		return ResponseEntity.ok(productoActualizadoDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable int id) {
		productoServi.eliminarProducto(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}