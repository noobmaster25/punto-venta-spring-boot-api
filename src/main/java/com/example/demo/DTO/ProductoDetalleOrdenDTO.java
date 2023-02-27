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
public class ProductoDetalleOrdenDTO {
	private Integer id_producto;
	private String descripcion;
  	private String nombre;
    private Double precio;
    private String categoria;
    
    public ProductoDetalleOrdenDTO(Producto producto) {
        this.id_producto = producto.getId_producto();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
 
        if (producto.getCategoria() != null) {
            this.categoria = producto.getCategoria().getNombre();
        } else {
            this.categoria = "sin categoria";
        }
    }
}
