package com.example.demo.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_cliente;
	private String nombre;
	private String username;
	private String email;
	private String direccion;
	private String telefono;
	private String tipo;
	private String password;
	
	public Cliente(Cliente cliente) {
		this.nombre = cliente.getNombre();
		this.username = cliente.getUsername();
		this.email = cliente.getEmail();
		this.direccion = cliente.getDireccion();
		this.telefono = cliente.getTelefono();
		this.tipo = cliente.getTipo();
		this.password = cliente.getPassword();
	}

	
}