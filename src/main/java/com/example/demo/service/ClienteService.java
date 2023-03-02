package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.DTO.ClienteNuevoDTO;
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

	public ClienteDTO guardarCliente(ClienteNuevoDTO clienteDto) {
		Cliente nuevoCliente = new Cliente(clienteDto.getNombre(), clienteDto.getUsername(), clienteDto.getEmail(),
				clienteDto.getDireccion(), clienteDto.getTelefono(), clienteDto.getTipo(), clienteDto.getPassword());

		clienteRepo.save(nuevoCliente);
		return new ClienteDTO(nuevoCliente);
	}

	public void eliminarClinetePorId(Integer id) throws Exception {
		Optional<Cliente> clienteOptional = clienteRepo.findById(id);
		if (clienteOptional.isEmpty()) {
			throw new Exception("elemento no exsite");
		}
		clienteRepo.deleteById(id);
	}

	public ClienteDTO actualizarPorId(Integer id, ClienteNuevoDTO clienteDto) throws Exception {
		Optional<Cliente> clienteOptional = clienteRepo.findById(id);
		if (clienteOptional.isEmpty()) {
			throw new Exception("elemento no exsite");
		}

		Cliente clienteActualizado = clienteOptional.get();

		clienteActualizado.setNombre(clienteDto.getNombre());
		clienteActualizado.setUsername(clienteDto.getUsername());
		clienteActualizado.setEmail(clienteDto.getEmail());
		clienteActualizado.setDireccion(clienteDto.getDireccion());
		clienteActualizado.setTelefono(clienteDto.getTelefono());
		clienteActualizado.setPassword(clienteDto.getPassword());
		clienteRepo.save(clienteActualizado);

		return new ClienteDTO(clienteActualizado);
	}
}