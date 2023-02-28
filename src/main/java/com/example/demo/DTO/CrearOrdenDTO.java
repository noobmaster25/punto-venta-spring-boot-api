package com.example.demo.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.DetalleOrden;
import com.example.demo.entities.Orden;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CrearOrdenDTO {
	
	private String numero;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaCreacion;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaRecibida;
	private Integer id_cliente;
	private List<DetalleOrdenSimpleDTO> detallesOrden;
}
