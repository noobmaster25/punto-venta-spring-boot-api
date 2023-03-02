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
public class ClienteSimpleDTO {
	
	private Integer id_cliente;
	private String nombre;
	private String telefono;
	private String tipo;
	
	public ClienteSimpleDTO(Cliente cliente) {
		this.id_cliente = cliente.getId_cliente();
		this.nombre = cliente.getNombre();
		this.telefono = cliente.getTelefono();
		this.tipo = cliente.getTipo();
	}
	
}