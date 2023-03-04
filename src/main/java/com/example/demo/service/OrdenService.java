package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.OrdenNuevaDTO;
import com.example.demo.DTO.DetalleOrdenSimpleDTO;
import com.example.demo.DTO.OrdenDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.DetalleOrden;
import com.example.demo.entities.Orden;
import com.example.demo.entities.Producto;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.IClienteRepository;
import com.example.demo.repository.IDetalleOrden;
import com.example.demo.repository.IOrdenRepository;
import com.example.demo.repository.IProductoRepository;

@Service
public class OrdenService {

	@Autowired
	private IOrdenRepository ordenRepo;

	@Autowired
	private IClienteRepository clienteRepo;

	@Autowired
	private IProductoRepository productoRepo;

	@Autowired
	private IDetalleOrden detalleRepo;

	public List<OrdenDTO> obtenerTodos() {
		List<Orden> listaOrden = ordenRepo.findAll();

		return listaOrden.stream().map(o -> new OrdenDTO(o)).collect(Collectors.toList());

	}

	public OrdenDTO obtenerPorId(Integer id) {
		Optional<Orden> orden = ordenRepo.findById(id);
		if (orden.isEmpty()) {
			throw new NotFoundException("no se encontro orden con id:" + id);
		}
		return new OrdenDTO(orden.get());
	}

	public OrdenDTO guardarOrden(OrdenNuevaDTO orden) {
		Optional<Cliente> clienteOptional = clienteRepo.findById(orden.getId_cliente());
		if (clienteOptional.isEmpty()) {
			throw new NotFoundException("no se encontro cliente con id:" + orden.getId_cliente());
		}

		Orden nuevaOrden = new Orden(orden.getNumero(), orden.getFechaCreacion(), orden.getFechaRecibida(),
				clienteOptional.get());
		ordenRepo.save(nuevaOrden);

		List<DetalleOrden> listaDetalle = new ArrayList<>();
		for (DetalleOrdenSimpleDTO detalleDTO : orden.getDetallesOrden()) {
			Optional<Producto> productoOptional = productoRepo.findById(detalleDTO.getId_producto());
			if (productoOptional.isEmpty()) {
				throw new NotFoundException("no se encontro producto con id:" + detalleDTO.getId_producto());
			}
			DetalleOrden detalleOrden = new DetalleOrden(detalleDTO.getNombre(), detalleDTO.getCantidad(),
					detalleDTO.getPrecio(), detalleDTO.getTotal(), nuevaOrden, productoOptional.get());

			detalleRepo.save(detalleOrden);

			listaDetalle.add(detalleOrden);
		}
		nuevaOrden.setDetalleOrden(listaDetalle);

		return new OrdenDTO(nuevaOrden);
	}

	public OrdenDTO actualizarOrden(Integer id, OrdenNuevaDTO ordenDto) {
		Optional<Orden> ordenAnterior = ordenRepo.findById(id);
		if (ordenAnterior.isEmpty()) {
			throw new NotFoundException("no se encontro orden con id:" + id);
		}
		Optional<Cliente> cliente = clienteRepo.findById(ordenDto.getId_cliente());
		if (cliente.isEmpty()) {
			throw new NotFoundException("no se encontro cliente con id:" + ordenDto.getId_cliente());
		}
		Orden ordenActualizada = ordenAnterior.get();
		ordenActualizada.setCliente(cliente.get());
		ordenActualizada.setFechaCreacion(ordenDto.getFechaCreacion());
		ordenActualizada.setFechaRecibida(ordenDto.getFechaRecibida());
		ordenActualizada.setNumero(ordenDto.getNumero());

		List<DetalleOrden> listaDetalle = new ArrayList<>();

		for (DetalleOrdenSimpleDTO detalleDTO : ordenDto.getDetallesOrden()) {
			Optional<Producto> productoOptional = productoRepo.findById(detalleDTO.getId_producto());
			if (productoOptional.isEmpty()) {
				throw new NotFoundException("no se encontro producto con id:" + detalleDTO.getId_producto());
			}
			DetalleOrden detalleOrden = new DetalleOrden(detalleDTO.getNombre(), detalleDTO.getCantidad(),
					detalleDTO.getPrecio(), detalleDTO.getTotal(), ordenAnterior.get(), productoOptional.get());

			detalleRepo.save(detalleOrden);

			listaDetalle.add(detalleOrden);
		}
		ordenActualizada.setDetalleOrden(listaDetalle);

		ordenRepo.save(ordenActualizada);
		return new OrdenDTO(ordenActualizada);
	}

	public void eliminarPorId(Integer id) {
		Optional<Orden> ordenAnterior = ordenRepo.findById(id);
		if (ordenAnterior.isEmpty()) {
			throw new NotFoundException("no se encontro orden con id:" + id);
		}
		ordenRepo.deleteById(id);
	}
}
