package com.example.demo.DTO;

import com.example.demo.entities.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteNuevoDTO {
	@NotBlank
	private String nombre;
	@NotBlank
	private String username;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String direccion;
	@NotBlank
	private String telefono;
	@NotBlank
	private String tipo;
	@Size(min = 5, max = 15)
	private String password;

	public ClienteNuevoDTO(Cliente cliente) {
		this.nombre = cliente.getNombre();
		this.username = cliente.getUsername();
		this.email = cliente.getEmail();
		this.direccion = cliente.getDireccion();
		this.telefono = cliente.getTelefono();
		this.tipo = cliente.getTipo();
		this.password = cliente.getPassword();
	}

}