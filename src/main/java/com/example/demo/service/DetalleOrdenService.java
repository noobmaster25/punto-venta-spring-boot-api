package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.DetalleOrdenDTO;
import com.example.demo.DTO.ProductoDetalleOrdenDTO;
import com.example.demo.entities.DetalleOrden;
import com.example.demo.repository.IDetalleOrden;

@Service
public class DetalleOrdenService {

	@Autowired
	private IDetalleOrden detalleRepo;
	
	public List<DetalleOrdenDTO> obtenerTodos() {
		List<DetalleOrden> detalles = detalleRepo.findAll();
		List<DetalleOrdenDTO> detallesDto = detalles.stream().map(d-> new DetalleOrdenDTO(d.getId_detalle_orden(),
				d.getCantidad(),
				d.getPrecio(),
				d.getTotal(),
				d.getOrden().getId_orden(),
				new ProductoDetalleOrdenDTO(d.getProductos()))).collect(Collectors.toList());
		return detallesDto;
	}
	public DetalleOrden obtenerPorId(Integer id) throws Exception {
		Optional<DetalleOrden> detalleOptional = detalleRepo.findById(id);
		if (detalleOptional.isEmpty()) throw new Exception("sin elemento");
		return detalleOptional.get();
		
	}
	public DetalleOrden guardarDetalle(DetalleOrden detalleOrden) {
		return detalleRepo.save(detalleOrden);
	}
	public void eliminarDetalle(Integer id) throws Exception {
		Optional<DetalleOrden> detalleOptional = detalleRepo.findById(id);
		if (detalleOptional.isEmpty()) throw new Exception("sin elemento");
		detalleRepo.deleteById(id);
		
	}
	public DetalleOrden actualizarDetalle(Integer id, DetalleOrden detalleOrden) throws Exception {
		Optional<DetalleOrden> detalleOptional = detalleRepo.findById(id);
		if (detalleOptional.isEmpty()) throw new Exception("sin elemento");
		
		DetalleOrden detalleActualizada = detalleOptional.get();
		detalleActualizada.setCantidad(detalleOrden.getCantidad());
		detalleActualizada.setNombre(detalleOrden.getNombre());
		detalleActualizada.setOrden(detalleOrden.getOrden());
		detalleActualizada.setPrecio(detalleOrden.getPrecio());
		detalleActualizada.setProductos(detalleOrden.getProductos());
		detalleActualizada.setTotal(detalleOrden.getTotal());
		return detalleRepo.save(detalleActualizada);
		
	}
	
}