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
	@NotBlank(message = "el nombre es obligatorio")
	private String nombre;
	@NotBlank(message = "descripcion es obligatorio")
	private String descripcion;
	
	@NotNull
	@Positive(message = "la cantidad debe ser mayor a cero")
	private Integer cantidad;
	
	@NotNull
	@DecimalMin(value = "0.1", message = "precio debe ser mayor a 0.1")
    private Double precio;
	
	@NotNull
	@Positive
    private Integer id_categoria;
}
