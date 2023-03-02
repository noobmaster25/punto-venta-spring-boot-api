package com.example.demo.DTO;

import com.example.demo.entities.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
	private int id_categoria;
	private String nombre;
	private String descripcion;
	
	public CategoriaDTO(Categoria categoria) {
		this.id_categoria = categoria.getId_categoria();
		this.nombre = categoria.getNombre();
		this.descripcion = categoria.getDescripcion();
	}
}
