package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaNuevaDTO {
	@NotBlank
	private String nombre;
	@NotBlank
	private String descripcion;
	
}
