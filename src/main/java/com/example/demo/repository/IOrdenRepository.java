package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Orden;


@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer> {

}
