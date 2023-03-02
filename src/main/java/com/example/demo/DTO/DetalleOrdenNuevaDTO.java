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
public class DetalleOrdenNuevaDTO {
	private Integer id_detalle_orden;
	private double cantidad;
	private String nombre;
	private double precio;
	private double total;
	private Integer id_orden;
	private Integer id_producto;
}
