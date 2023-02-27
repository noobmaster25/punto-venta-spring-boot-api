package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.repository.IClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private IClienteRepository clienteRepo;

	public List<ClienteDTO> obtenerTodos() throws Exception {
		List<Cliente> clientes = clienteRepo.findAll();
		return clientes.stream().map(c -> new ClienteDTO(c)).collect(Collectors.toList());
	}

	public ClienteDTO obtenerPorId(Integer id) throws Exception {
		Optional<Cliente> clienteOptional = clienteRepo.findById(id);
		if (clienteOptional.isEmpty()) {
			throw new Exception("elemento no exsite");
		}
		return new ClienteDTO(clienteOptional.get());
	}

	public ClienteDTO guardarCliente(Cliente cliente) {
		return new ClienteDTO(clienteRepo.save(cliente));
	}

	public void eliminarClinetePorId(Integer id) throws Exception {
		Optional<Cliente> clienteOptional = clienteRepo.findById(id);
		if (clienteOptional.isEmpty()) {
			throw new Exception("elemento no exsite");
		}
		clienteRepo.deleteById(id);
	}

	public Cliente actualizarPorId(Integer id, Cliente cliente) throws Exception {
		Optional<Cliente> clienteOptional = clienteRepo.findById(id);
		if (clienteOptional.isEmpty()) {
			throw new Exception("elemento no exsite");
		}

		Cliente clienteActualizado = new Cliente(cliente);
		clienteActualizado.setId_cliente(clienteOptional.get().getId_cliente());
		System.out.println(clienteActualizado);

		return clienteRepo.save(clienteActualizado);
	}
}