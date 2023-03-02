package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.DetalleOrdenDTO;
import com.example.demo.DTO.DetalleOrdenNuevaDTO;
import com.example.demo.DTO.ProductoDetalleOrdenDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.DetalleOrden;
import com.example.demo.entities.Orden;
import com.example.demo.entities.Producto;
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

	public List<DetalleOrdenDTO> obtenerTodos() {
		List<DetalleOrden> detalles = detalleRepo.findAll();
		List<DetalleOrdenDTO> detallesDto = detalles.stream()
				.map(d -> new DetalleOrdenDTO(d.getId_detalle_orden(), d.getCantidad(), d.getPrecio(), d.getTotal(),
						d.getOrden().getId_orden(), new ProductoDetalleOrdenDTO(d.getProductos())))
				.collect(Collectors.toList());
		return detallesDto;
	}

	public DetalleOrdenDTO obtenerPorId(Integer id) throws Exception {
		Optional<DetalleOrden> detalleOptional = detalleRepo.findById(id);
		if (detalleOptional.isEmpty())
			throw new Exception("sin elemento");
		return new DetalleOrdenDTO(detalleOptional.get());

	}

	public DetalleOrdenDTO guardarDetalle(DetalleOrdenNuevaDTO detalleOrdenDto) throws Exception {
		Optional<Orden> orden = ordenRepo.findById(detalleOrdenDto.getId_orden());
		Optional<Producto> producto = productoRepo.findById(detalleOrdenDto.getId_producto());
		if (orden.isEmpty()) {
			throw new Exception("no existe orden");
		}
		if (producto.isEmpty()) {
			throw new Exception("no existe producto");
		}
		DetalleOrden detalleOrdenNueva = new DetalleOrden(detalleOrdenDto.getNombre(), detalleOrdenDto.getCantidad(),
				detalleOrdenDto.getPrecio(), detalleOrdenDto.getTotal(), orden.get(), producto.get());
		detalleRepo.save(detalleOrdenNueva);

		return new DetalleOrdenDTO(detalleOrdenNueva);
	}

	public void eliminarDetalle(Integer id) throws Exception {
		Optional<DetalleOrden> detalleOptional = detalleRepo.findById(id);
		if (detalleOptional.isEmpty())
			throw new Exception("sin elemento");
		detalleRepo.deleteById(id);

	}

	public DetalleOrdenDTO actualizarDetalle(Integer id, DetalleOrdenNuevaDTO detalleOrdenDto) throws Exception {
		Optional<DetalleOrden> detalleOptional = detalleRepo.findById(id);
		if (detalleOptional.isEmpty())
			throw new Exception("sin elemento");

		Optional<Orden> orden = ordenRepo.findById(detalleOrdenDto.getId_orden());
		if (orden.isEmpty())
			throw new Exception("no existe orden");

		Optional<Producto> producto = productoRepo.findById(detalleOrdenDto.getId_producto());
		if (producto.isEmpty())
			throw new Exception("producto no existe");

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