package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.DetalleOrdenDTO;
import com.example.demo.DTO.DetalleOrdenNuevaDTO;
import com.example.demo.DTO.ProductoDetalleOrdenDTO;
import com.example.demo.entities.DetalleOrden;
import com.example.demo.entities.Orden;
import com.example.demo.entities.Producto;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.IDetalleOrden;
import com.example.demo.repository.IOrdenRepository;
import com.example.demo.repository.IProductoRepository;

@Service
public class DetalleOrdenService {

	@Autowired
	private IDetalleOrden detalleRepo;

	@Autowired
	private IOrdenRepository ordenRepo;

	@Autowired
	private IProductoRepository productoRepo;

	public Page<DetalleOrdenDTO> obtenerTodos(int noPagina, int tamanioPagina) {
		Pageable pageable = PageRequest.of(noPagina, tamanioPagina);
		Page<DetalleOrden> detallesOrden = detalleRepo.findAll(pageable);
		List<DetalleOrdenDTO> detallesDto = detallesOrden.
				getContent().
				stream()
				.map(d -> new DetalleOrdenDTO(d.getId_detalle_orden(), 
						d.getCantidad(), 
						d.getPrecio(), 
						d.getTotal(),
						d.getOrden().
						getId_orden(), 
						new ProductoDetalleOrdenDTO(d.getProductos())))
						.collect(Collectors.toList());
		
		return new PageImpl<>(detallesDto, pageable, detallesOrden.getTotalElements());
	}

	public DetalleOrdenDTO obtenerPorId(Integer id) {
		Optional<DetalleOrden> detalleOptional = detalleRepo.findById(id);
		if (detalleOptional.isEmpty())
			throw new NotFoundException("no se encontro detalle orden con id:" + id);
		return new DetalleOrdenDTO(detalleOptional.get());

	}

	public DetalleOrdenDTO guardarDetalle(DetalleOrdenNuevaDTO detalleOrdenDto) {
		Optional<Orden> orden = ordenRepo.findById(detalleOrdenDto.getId_orden());
		Optional<Producto> producto = productoRepo.findById(detalleOrdenDto.getId_producto());
		if (orden.isEmpty()) {
			throw new NotFoundException("no se encontro orden asociado al detalle orden");
		}
		if (producto.isEmpty()) {
			throw new NotFoundException("no se encontro el producto que desea almacenar");
		}
		DetalleOrden detalleOrdenNueva = new DetalleOrden(detalleOrdenDto.getNombre(), detalleOrdenDto.getCantidad(),
				detalleOrdenDto.getPrecio(), detalleOrdenDto.getTotal(), orden.get(), producto.get());
		detalleRepo.save(detalleOrdenNueva);

		return new DetalleOrdenDTO(detalleOrdenNueva);
	}

	public void eliminarDetalle(Integer id) {
		Optional<DetalleOrden> detalleOptional = detalleRepo.findById(id);
		if (detalleOptional.isEmpty())
			throw new NotFoundException("no se encontro detalle orden con id:" + id);
		detalleRepo.deleteById(id);

	}

	public DetalleOrdenDTO actualizarDetalle(Integer id, DetalleOrdenNuevaDTO detalleOrdenDto) {
		Optional<DetalleOrden> detalleOptional = detalleRepo.findById(id);
		if (detalleOptional.isEmpty())
			throw new NotFoundException("no se encontro detalle orden con id:" + id);

		Optional<Orden> orden = ordenRepo.findById(detalleOrdenDto.getId_orden());
		if (orden.isEmpty())
			throw new NotFoundException("no se encontro la orden asociada al detalle orden");

		Optional<Producto> producto = productoRepo.findById(detalleOrdenDto.getId_producto());
		if (producto.isEmpty())
			throw new NotFoundException("no se encontro el producto con el que desea actualizar");

		DetalleOrden detalleActualizado = detalleOptional.get();
		detalleActualizado.setCantidad(detalleOrdenDto.getCantidad());
		detalleActualizado.setNombre(detalleOrdenDto.getNombre());
		detalleActualizado.setOrden(orden.get());
		detalleActualizado.setPrecio(detalleOrdenDto.getPrecio());
		detalleActualizado.setProductos(producto.get());
		detalleActualizado.setTotal(detalleOrdenDto.getTotal());

		detalleRepo.save(detalleActualizado);
		return new DetalleOrdenDTO(detalleActualizado);

	}

}