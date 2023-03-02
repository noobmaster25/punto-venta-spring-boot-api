package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ProductoDTO;
import com.example.demo.DTO.ProductoNuevoDTO;
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
		List<ProductoDTO> listaProductos = productos.stream().map(p -> new ProductoDTO(p)).collect(Collectors.toList());

		return listaProductos;
	}

	public ProductoDTO crearProducto(ProductoNuevoDTO productoDto) throws Exception {
		Optional<Categoria> categoria = categoriaRepo.findById(productoDto.getId_categoria());
		if (categoria.isEmpty()) {
			throw new Exception("categoria no encontrada");
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
		repoProducto.deleteById(id);
	}

	public ProductoDTO actualizarProducto(int id, ProductoNuevoDTO productoDto) throws Exception {
		Optional<Producto> productoAnterior = repoProducto.findById(id);
		if (productoAnterior.isEmpty()) {
			throw new Exception("producto no encontrado");
		}
		Optional<Categoria> categoriaActualizada = categoriaRepo.findById(productoDto.getId_categoria());
		if (categoriaActualizada.isEmpty()) {
			throw new Exception("categoria no existe");
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