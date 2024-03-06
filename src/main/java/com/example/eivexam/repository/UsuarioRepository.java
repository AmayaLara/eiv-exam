package com.example.eivexam.repository;

import com.example.eivexam.model.Usuario;
import com.example.eivexam.utils.ClaveCompuestaPersona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, ClaveCompuestaPersona> {
  Optional<Usuario> findByUsername(String username);
}
