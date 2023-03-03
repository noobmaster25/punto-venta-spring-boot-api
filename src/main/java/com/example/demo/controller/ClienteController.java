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

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.DTO.ClienteNuevoDTO;
import com.example.demo.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("clientes")
@CrossOrigin
public class ClienteController {

	@Autowired
	ClienteService clienteServi;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> obtenerTodos() throws Exception {
		return new ResponseEntity<>(clienteServi.obtenerTodos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable Integer id) throws Exception {
		return new ResponseEntity<>(clienteServi.obtenerPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> guardarCliente(@Valid @RequestBody ClienteNuevoDTO clienteDto) {
		return new ResponseEntity<>(clienteServi.guardarCliente(clienteDto), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id) throws Exception {
		clienteServi.eliminarClinetePorId(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> actualizarClientePorId(@PathVariable Integer id,
			@Valid @RequestBody ClienteNuevoDTO clienteDto) throws Exception {
		return ResponseEntity.ok(clienteServi.actualizarPorId(id, clienteDto));
	}

}
