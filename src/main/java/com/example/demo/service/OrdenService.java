package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CrearOrdenDTO;
import com.example.demo.DTO.DetalleOrdenSimpleDTO;
import com.example.demo.DTO.OrdenDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.DetalleOrden;
import com.example.demo.entities.Orden;
import com.example.demo.entities.Producto;
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
	
	public List<OrdenDTO> obtenerTodos(){
		List<Orden> listaOrden = ordenRepo.findAll();
	
		return listaOrden.stream().map(o-> new OrdenDTO(o)).collect(Collectors.toList()); 

	}
	
	public OrdenDTO obtenerPorId(Integer id) throws Exception {
		Optional<Orden> orden = ordenRepo.findById(id);
		if (orden.isEmpty()) {
			throw new Exception("elemento no existe");
		}
		return new OrdenDTO(orden.get());
	}
	
	public OrdenDTO guardarOrden(CrearOrdenDTO orden) throws Exception {
		Optional<Cliente> clienteOptional = clienteRepo.findById(orden.getId_cliente());
		if (clienteOptional.isEmpty()) {
			throw new Exception("el cliente no existe");
		}
		Orden nuevaOrden = new Orden(orden.getNumero(),orden.getFechaCreacion(),orden.getFechaRecibida(),clienteOptional.get());
		ordenRepo.save(nuevaOrden);
		List<DetalleOrden> listaDetalle = new ArrayList<>();
		for (DetalleOrdenSimpleDTO detalleDTO : orden.getDetallesOrden()) {
			Optional<Producto> productoOptional = productoRepo.findById(detalleDTO.getId_producto()); 
			if (productoOptional.isEmpty()) {
				throw new Exception("produto no existe");
			}
			DetalleOrden detalleOrden = new DetalleOrden(detalleDTO.getNombre()
					,detalleDTO.getCantidad()
					,detalleDTO.getPrecio()
					,detalleDTO.getTotal()
					,nuevaOrden
					,productoOptional.get());
			
			detalleRepo.save(detalleOrden);
			
			listaDetalle.add(detalleOrden);	
		}
		nuevaOrden.setDetalleOrden(listaDetalle);
				
		return new OrdenDTO(nuevaOrden);
	}
	
	public Orden actualizarOrden(Integer id, Orden orden) throws Exception {
		Optional<Orden> ordenAnterior = ordenRepo.findById(id);
		if (ordenAnterior.isEmpty()) {
			throw new Exception("elemento no existe");
		}
		Orden ordenActualizada = ordenAnterior.get();
		ordenActualizada.setCliente(orden.getCliente());
		ordenActualizada.setDetalleOrden(orden.getDetalleOrden());
		ordenActualizada.setFechaCreacion(orden.getFechaCreacion());
		ordenActualizada.setFechaRecibida(orden.getFechaRecibida());
		ordenActualizada.setNumero(orden.getNumero());
		
		return ordenActualizada;
	}
	public void eliminarPorId(Integer id) throws Exception {
		Optional<Orden> ordenAnterior = ordenRepo.findById(id);
		if (ordenAnterior.isEmpty()) {
			throw new Exception("elemento no existe");
		}
		ordenRepo.deleteById(id);
	}
}
