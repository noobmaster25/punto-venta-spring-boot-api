package com.example.demo.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ProductoDTO;
import com.example.demo.entities.Categoria;
import com.example.demo.entities.Producto;
import com.example.demo.repository.ICategoriaRepository;
import com.example.demo.repository.IProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private IProductoRepository repoProducto;
	
	@Autowired
	private ICategoriaRepository categoriaRepo;

	public ProductoDTO obtenerPorId(int id) throws Exception {
		Optional<Producto> producto = repoProducto.findById(id);
		if (producto.isEmpty()) {
			throw new Exception("no encontrado");
		}
		return new ProductoDTO(producto.get());
	}

	public List<ProductoDTO> obtenerTodos() {	
		List<Producto> productos = repoProducto.findAll();
		System.out.println(productos.get(0));
		List<ProductoDTO> listaProductos = productos.stream().map(
				p -> new ProductoDTO(p))
				.collect(Collectors.toList());

				return 	listaProductos;
	}

	public Producto crearProducto(Producto producto) throws Exception {
		Optional<Categoria> categoria = categoriaRepo.findById(producto.getCategoria().getId_categoria());
		if (categoria.isEmpty()) {
			throw new Exception("categoria no encontrada");
		}
		producto.setCategoria(categoria.get());
		return repoProducto.save(producto);

	}

	public void eliminarProducto(int id) {
		repoProducto.deleteById(id);
	}

	public Producto actualizarProducto(int id, Producto producto) throws Exception {
		Optional<Producto> productoAnterior = repoProducto.findById(id);
		if (productoAnterior.isEmpty()) {
			throw new Exception("producto no encontrado");
		}
		Producto productoActualizado = productoAnterior.get();
		productoActualizado.setNombre(producto.getNombre());
		productoActualizado.setDescripcion(producto.getDescripcion());
		productoActualizado.setPrecio(producto.getPrecio());
		productoActualizado.setCantidad(producto.getCantidad());
		productoActualizado.setCategoria(producto.getCategoria());

		return repoProducto.save(productoActualizado);

	}

}