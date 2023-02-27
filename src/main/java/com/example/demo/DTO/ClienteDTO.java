package com.example.demo.DTO;


import com.example.demo.entities.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
	
	private Integer id_cliente;
	private String nombre;
	private String username;
	private String email;
	private String direccion;
	private String telefono;
	private String tipo;
	
	public ClienteDTO(Cliente cliente) {
		this.id_cliente = cliente.getId_cliente();
		this.nombre = cliente.getNombre();
		this.username = cliente.getUsername();
		this.email = cliente.getEmail();
		this.direccion = cliente.getDireccion();
		this.telefono = cliente.getTelefono();
		this.tipo = cliente.getTipo();
	}
	
}
