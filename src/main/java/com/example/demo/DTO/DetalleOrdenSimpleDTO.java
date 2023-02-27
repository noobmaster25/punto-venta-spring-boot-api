package com.example.demo.DTO;

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
public class DetalleOrdenSimpleDTO {
	private double cantidad;
	private double precio;
	private double total;
	private String nombre;
	private Integer id_producto;
}

