package com.example.demo.DTO;

import com.example.demo.entities.DetalleOrden;

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
public class DetalleOrdenDTO {

	private Integer id_detalle_orden;
	private double cantidad;
	private double precio;
	private double total;
	private Integer id_orden;
	private ProductoDetalleOrdenDTO producto;
	
	
	public DetalleOrdenDTO(DetalleOrden detalle) {
		this.id_detalle_orden = detalle.getId_detalle_orden();
		this.cantidad = detalle.getCantidad();
		this.precio = detalle.getPrecio();
		this.total = detalle.getTotal();
		this.id_orden = detalle.getOrden().getId_orden();
		this.producto = new ProductoDetalleOrdenDTO(detalle.getProductos());
	}
}