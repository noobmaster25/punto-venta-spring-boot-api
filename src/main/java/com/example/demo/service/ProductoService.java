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

import com.example.demo.DTO.ProductoDTO;
import com.example.demo.DTO.ProductoNuevoDTO;
import com.example.demo.entities.Categoria;
import com.example.demo.entities.Producto;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.ICategoriaRepository;
import com.example.demo.repository.IProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private IProductoRepository repoProducto;

	@Autowired
	private ICategoriaRepository categoriaRepo;

	public ProductoDTO obtenerPorId(int id) {
		Optional<Producto> producto = repoProducto.findById(id);
		if (producto.isEmpty()) {
			throw new NotFoundException("no se encontro producto con id:"+id);
		}
		return new ProductoDTO(producto.get());
	}

	public Page<ProductoDTO> obtenerTodos(int noPagina, int tamanioPagina) {
		Pageable pageable = PageRequest.of(noPagina, tamanioPagina);
		Page<Producto> productos = repoProducto.findAll(pageable);
		List<ProductoDTO> productosDto = productos.getContent().stream().map(p -> new ProductoDTO(p)).collect(Collectors.toList());
		return new PageImpl<>(productosDto, pageable, productos.getTotalElements());
	}

	public ProductoDTO crearProducto(ProductoNuevoDTO productoDto) {
		Optional<Categoria> categoria = categoriaRepo.findById(productoDto.getId_categoria());
		if (categoria.isEmpty()) {
			throw new NotFoundException("no se encontro categoria con el id:"+productoDto.getId_categoria());
		}
		Producto productoCreadoDto = new Producto(productoDto.getNombre()
												, productoDto.getDescripcion()
												,productoDto.getCantidad()
												, productoDto.getPrecio()
												,categoria.get());
		repoProducto.save(productoCreadoDto);

		return new ProductoDTO(productoCreadoDto);

	}

	public void eliminarProducto(int id) {
		Optional<Producto> productoOptional = repoProducto.findById(id);
		if (productoOptional.isEmpty()) {
			throw new NotFoundException("no se encontro produto con id:"+id);
		}
		repoProducto.deleteById(id);
	}

	public ProductoDTO actualizarProducto(int id, ProductoNuevoDTO productoDto) {
		Optional<Producto> productoAnterior = repoProducto.findById(id);
		if (productoAnterior.isEmpty()) {
			throw new NotFoundException("no se encontro produto con id:"+id);
		}
		Optional<Categoria> categoriaActualizada = categoriaRepo.findById(productoDto.getId_categoria());
		if (categoriaActualizada.isEmpty()) {
			throw new NotFoundException("no se encontro la categoria a actualizar con id:"+productoDto.getId_categoria());
		}
		Producto productoActualizado = productoAnterior.get();
		productoActualizado.setNombre(productoDto.getNombre());
		productoActualizado.setDescripcion(productoDto.getDescripcion());
		productoActualizado.setPrecio(productoDto.getPrecio());
		productoActualizado.setCantidad(productoDto.getCantidad());
		productoActualizado.setCategoria(categoriaActualizada.get());
		
		repoProducto.save(productoActualizado);
		
		

		return new ProductoDTO(productoActualizado);

	}

}