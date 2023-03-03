package com.example.demo.DTO;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
	@NotNull
	@Min(value = 1)
	private double cantidad;
	
	private String nombre;
	
	@NotNull
	@DecimalMin(value = "0.1")
	private double precio;
	
	@DecimalMin(value = "0.1")
	private double total;
	
	@NotNull
	@Positive
	private Integer id_orden; 
	
	@NotNull
	@Positive
	private Integer id_producto;
}
