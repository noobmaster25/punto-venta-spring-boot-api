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
public class OrdenDTO {

	private Integer id_orden;
	private String numero;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaCreacion;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaRecibida;
	private ClienteSimpleDTO cliente;
	private List<DetalleOrdenDTO> detalleOrden;
	
	public OrdenDTO(Orden orden) {
		this.id_orden = orden.getId_orden();
		this.numero = orden.getNumero();
		this.fechaCreacion = orden.getFechaCreacion();
		this.fechaRecibida = orden.getFechaRecibida();
		this.cliente =new ClienteSimpleDTO(orden.getCliente());
		List<DetalleOrdenDTO> listaDetalles = new ArrayList<>();
		for (DetalleOrden detalleOrden : orden.getDetalleOrden()) {
			listaDetalles.add(new DetalleOrdenDTO(detalleOrden));
		}
		this.detalleOrden = listaDetalles;
		
		
	}
}