package com.example.eivexam.controller;

import com.example.eivexam.service.TiposDocumentoService;
import com.example.eivexam.utils.dto.TiposDocumentoDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos")
@RequiredArgsConstructor
public class TiposDocumentoController {

  private final TiposDocumentoService tiposDocumentoService;

  @GetMapping
  public ResponseEntity<List<TiposDocumentoDto>> getAll() {
    return ResponseEntity.ok(tiposDocumentoService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TiposDocumentoDto> getById(@PathVariable("id") Integer id) throws BadRequestException {
    return ResponseEntity.ok(tiposDocumentoService.getById(id));
  }

  @PostMapping
  public ResponseEntity<TiposDocumentoDto> save(@RequestBody TiposDocumentoDto request) throws BadRequestException {
    return new ResponseEntity<TiposDocumentoDto>(tiposDocumentoService.save(request), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TiposDocumentoDto> update(@PathVariable Integer id,
                                                  @RequestBody TiposDocumentoDto request) throws BadRequestException {
    return ResponseEntity.ok(tiposDocumentoService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws BadRequestException {
    return tiposDocumentoService.delete(id);
  }
}
