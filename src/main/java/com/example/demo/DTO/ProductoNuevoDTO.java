package com.example.demo.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoNuevoDTO {
	@NotBlank
	private String nombre;
	@NotBlank
	private String descripcion;
	
	@NotNull
	@Positive
	private Integer cantidad;
	
	@NotNull
	@DecimalMin(value = "0.1", message = "precio debe ser mayor a 0.1")
    private Double precio;
	
	@NotNull
	@Positive
    private Integer id_categoria;
}
