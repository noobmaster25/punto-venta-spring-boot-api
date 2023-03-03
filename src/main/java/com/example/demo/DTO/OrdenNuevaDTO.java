package com.example.demo.DTO;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenNuevaDTO {
	@NotBlank
	private String numero;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaCreacion;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaRecibida;

	@NotNull
	@Positive
	private Integer id_cliente;

	@NotEmpty
	private List<DetalleOrdenSimpleDTO> detallesOrden;
}
