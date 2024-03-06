package com.example.eivexam.repository;

import com.example.eivexam.model.TiposDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TiposDocumentoRepository extends JpaRepository<TiposDocumento, Integer> {

  Optional<TiposDocumento> findByAbreviatura(String abreviatura);

}
