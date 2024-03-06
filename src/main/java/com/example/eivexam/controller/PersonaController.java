package com.example.eivexam.controller;

import com.example.eivexam.service.PersonaService;
import com.example.eivexam.utils.dto.request.PersonaRequestDto;
import com.example.eivexam.utils.dto.response.PersonaResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
@RequiredArgsConstructor
public class PersonaController {

  private final PersonaService personaService;

  @GetMapping
  public ResponseEntity<List<PersonaResponseDto>> getAll() {
    return ResponseEntity.ok(personaService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonaResponseDto> getById(@PathVariable("id") Integer id,
                                                    @RequestParam String abreviatura) throws BadRequestException {
    return ResponseEntity.ok(personaService.getById(id, abreviatura));
  }

  @PostMapping
  public ResponseEntity<PersonaResponseDto> save(@RequestBody PersonaRequestDto request) throws BadRequestException {
    return new ResponseEntity<PersonaResponseDto>(personaService.save(request), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PersonaResponseDto> update(@PathVariable Integer id,
                                                   @RequestBody PersonaRequestDto request) throws BadRequestException {
    return ResponseEntity.ok(personaService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id,
                                     @RequestParam String abreviatura) throws BadRequestException {
    return personaService.delete(id, abreviatura);
  }
}
