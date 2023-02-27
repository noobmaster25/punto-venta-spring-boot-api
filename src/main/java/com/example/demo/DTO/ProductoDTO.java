package com.example.demo.DTO;

import com.example.demo.entities.Producto;

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
public class ProductoDTO {
		private Integer id_producto;
		private String nombre;
		private String descripcion;
		private Integer cantidad;
	    private Double precio;
	    private String categoria;
	    
	    public ProductoDTO(Producto producto) {
	        this.id_producto = producto.getId_producto();
	        this.nombre = producto.getNombre();
	        this.descripcion = producto.getDescripcion();
	        this.cantidad = producto.getCantidad();
	        this.precio = producto.getPrecio();
	 
	        if (producto.getCategoria() != null) {
	            this.categoria = producto.getCategoria().getNombre();
	        } else {
	            this.categoria = "sin categoria";
	        }
	    }
}
