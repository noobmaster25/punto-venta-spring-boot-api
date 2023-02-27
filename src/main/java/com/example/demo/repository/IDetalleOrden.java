package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.DetalleOrden;

@Repository
public interface IDetalleOrden extends JpaRepository<DetalleOrden, Integer> {

}
