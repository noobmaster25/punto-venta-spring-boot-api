package com.example.demo.entities;



import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_producto;
	private String nombre;
	private String descripcion;
	private int cantidad;
	private double precio;
	@ManyToOne
	@JoinColumn(name = "id_categoria",referencedColumnName = "id_categoria")
	private Categoria categoria;
	
	
	
	public Producto(String nombre, String descripcion, int cantidad, double precio, Categoria categoria) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.precio = precio;
		this.categoria = categoria;
	}
	
	

	
}
