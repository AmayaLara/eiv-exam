package com.example.eivexam.service.Impl;

import com.example.eivexam.model.Localidad;
import com.example.eivexam.model.Persona;
import com.example.eivexam.model.TiposDocumento;
import com.example.eivexam.repository.PersonaRepository;
import com.example.eivexam.service.LocalidadService;
import com.example.eivexam.service.PersonaService;
import com.example.eivexam.service.TiposDocumentoService;
import com.example.eivexam.utils.ClaveCompuestaPersona;
import com.example.eivexam.utils.dto.request.PersonaRequestDto;
import com.example.eivexam.utils.dto.response.PersonaResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PersonaServiceImpl implements PersonaService {

  private final PersonaRepository personaRepository;
  private final LocalidadService localidadService;
  private final ModelMapper modelMapper;
  private final TiposDocumentoService tiposDocumentoService;

  @Override
  public List<PersonaResponseDto> getAll() {
    return personaRepository.findAll()
        .stream().map(persona ->
            modelMapper.map(persona, PersonaResponseDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public PersonaResponseDto getById(Integer numeroDocumento, String abreviatura) throws BadRequestException {
    TiposDocumento tiposDocumento = tiposDocumentoService.findTipoDocumentoByAbreviatura(abreviatura);

    Persona personaEntity = this.findByClaveCompuesta(new ClaveCompuestaPersona(numeroDocumento, tiposDocumento));

    return modelMapper.map(personaEntity, PersonaResponseDto.class);
  }

  @Override
  public PersonaResponseDto save(PersonaRequestDto request) throws BadRequestException {

    TiposDocumento tiposDocumento = tiposDocumentoService
        .findTipoDocumentoByAbreviatura(request.getTipoDocumentoAbreviatura());

    var id = new ClaveCompuestaPersona(request.getNumeroDocumento(), tiposDocumento);

    if (personaRepository.findById(id).isPresent()) {
      throw new BadRequestException("La persona ya se encuentra ya registrada en el sistema");
    }

    Localidad localidad = localidadService.findLocalidadByNombre(request.getNombreLocalidad());

    if (request.getCodigoPostal().isEmpty()) {
      request.setCodigoPostal(localidad.getCodigoPostal());
    }

    Persona entityBuilt = Persona.builder()
        .id(id)
        .nombreCompleto(request.getNombrecompleto())
        .email(request.getEmail())
        .fechaNacimiento(request.getFechaNacimiento())
        .genero(request.getGenero().name())
        .esArgentino(request.getEsArgentino())
        .codigoPostal(request.getCodigoPostal())
        .localidad(localidad)
        .build();

    return modelMapper.map(personaRepository.save(entityBuilt), PersonaResponseDto.class);
  }

  @Override
  public PersonaResponseDto update(Integer numeroDocumento, PersonaRequestDto request)
      throws BadRequestException {

    TiposDocumento tiposDocumento = tiposDocumentoService.findTipoDocumentoByAbreviatura(
        request.getTipoDocumentoAbreviatura());

    Persona personaEntity = this.findByClaveCompuesta(new ClaveCompuestaPersona(numeroDocumento, tiposDocumento));

    Localidad localidad = localidadService.findLocalidadByNombre(request.getNombreLocalidad());

    personaEntity.setNombreCompleto(request.getNombrecompleto());
    personaEntity.setEmail(request.getEmail());
    personaEntity.setFechaNacimiento(request.getFechaNacimiento());
    personaEntity.setGenero(request.getGenero().name());
    personaEntity.setEsArgentino(request.getEsArgentino());
    personaEntity.setCodigoPostal(request.getCodigoPostal());
    personaEntity.setLocalidad(localidad);

    return modelMapper.map(personaEntity, PersonaResponseDto.class);
  }

  @Override
  public ResponseEntity<Void> delete(Integer numeroDocumento, String abreviatura) throws BadRequestException {

    TiposDocumento tiposDocumento = tiposDocumentoService.findTipoDocumentoByAbreviatura(abreviatura);

    Persona personaEntity = this.findByClaveCompuesta(new ClaveCompuestaPersona(numeroDocumento, tiposDocumento));

    personaRepository.delete(personaEntity);
    return ResponseEntity.noContent().build();
  }

  private Persona findByClaveCompuesta(ClaveCompuestaPersona id) throws BadRequestException {
    return personaRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("No se encontró persona con ese tipo y numero de documento"));

  }

}
