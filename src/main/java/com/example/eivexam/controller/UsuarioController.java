package com.example.eivexam.controller;

import com.example.eivexam.service.UsuarioService;
import com.example.eivexam.utils.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

  private final UsuarioService usuarioService;

  @GetMapping
  public ResponseEntity<List<UsuarioDto>> getAll() {
    return ResponseEntity.ok(usuarioService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuarioDto> getById(@PathVariable("id") Integer id,
                                            @RequestParam String abreviatura) throws BadRequestException {
    return ResponseEntity.ok(usuarioService.getById(id, abreviatura));
  }


  @PutMapping("/{id}")
  public ResponseEntity<UsuarioDto> update(@PathVariable Integer id,
                                           @RequestParam String abreviatura,
                                           @RequestBody UsuarioDto request) throws BadRequestException {
    return ResponseEntity.ok(usuarioService.update(id, abreviatura, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id,
                                     @RequestParam String abreviatura) throws BadRequestException {
    return usuarioService.delete(id, abreviatura);
  }
}
