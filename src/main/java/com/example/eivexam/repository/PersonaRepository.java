package com.example.eivexam.repository;

import com.example.eivexam.model.Persona;
import com.example.eivexam.utils.ClaveCompuestaPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends
    JpaRepository<Persona, ClaveCompuestaPersona> {
}

