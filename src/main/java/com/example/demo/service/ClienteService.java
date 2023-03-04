package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.DTO.ClienteNuevoDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.IClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private IClienteRepository clienteRepo;

	public Page<ClienteDTO> obtenerTodos(int noPagina, int tamanioPagina) {
		Pageable pageable = PageRequest.of(noPagina, tamanioPagina);
		Page<Cliente> clientes = clienteRepo.findAll(pageable);

		List<ClienteDTO> clientesDto = clientes.getContent().stream().map(c -> new ClienteDTO(c))
				.collect(Collectors.toList());
		return new PageImpl<>(clientesDto, pageable, clientes.getTotalElements());
	}

	public ClienteDTO obtenerPorId(Integer id) {
		Optional<Cliente> clienteOptional = clienteRepo.findById(id);
		if (clienteOptional.isEmpty()) {
			throw new NotFoundException("no se encontro cliente con id:" + id);
		}
		return new ClienteDTO(clienteOptional.get());
	}

	public ClienteDTO guardarCliente(ClienteNuevoDTO clienteDto) {
		Cliente nuevoCliente = new Cliente(clienteDto.getNombre(), clienteDto.getUsername(), clienteDto.getEmail(),
				clienteDto.getDireccion(), clienteDto.getTelefono(), clienteDto.getTipo(), clienteDto.getPassword());

		clienteRepo.save(nuevoCliente);
		return new ClienteDTO(nuevoCliente);
	}

	public void eliminarClinetePorId(Integer id) {
		Optional<Cliente> clienteOptional = clienteRepo.findById(id);
		if (clienteOptional.isEmpty()) {
			throw new NotFoundException("no se encontro cliente con id:" + id);
		}
		clienteRepo.deleteById(id);
	}

	public ClienteDTO actualizarPorId(Integer id, ClienteNuevoDTO clienteDto) {
		Optional<Cliente> clienteOptional = clienteRepo.findById(id);
		if (clienteOptional.isEmpty()) {
			throw new NotFoundException("no se encontro cliente con id:" + id);
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