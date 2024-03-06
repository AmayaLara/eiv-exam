package com.example.eivexam.repository;

import com.example.eivexam.model.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Integer> {
  Optional<Localidad> findByNombre(String nombre);
}
