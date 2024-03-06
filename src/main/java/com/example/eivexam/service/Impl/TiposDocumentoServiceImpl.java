package com.example.eivexam.service.Impl;

import com.example.eivexam.model.TiposDocumento;
import com.example.eivexam.repository.TiposDocumentoRepository;
import com.example.eivexam.service.TiposDocumentoService;
import com.example.eivexam.utils.dto.TiposDocumentoDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TiposDocumentoServiceImpl implements TiposDocumentoService {

  private final TiposDocumentoRepository tiposDocumentoRepository;
  private final ModelMapper modelMapper;


  public TiposDocumento findTipoDocumentoByAbreviatura(String abreviatura) throws BadRequestException {
    return tiposDocumentoRepository.findByAbreviatura(abreviatura)
        .orElseThrow(() -> new BadRequestException("tipo de documento no existe"));
  }

  @Override
  public List<TiposDocumentoDto> getAll() {
    return tiposDocumentoRepository.findAll()
        .stream().map(tiposDocumento ->
            modelMapper.map(tiposDocumento, TiposDocumentoDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public TiposDocumentoDto getById(Integer id) throws BadRequestException {
    return modelMapper.map(this.findTiposDocumentoById(id), TiposDocumentoDto.class);
  }

  @Override
  public TiposDocumentoDto save(TiposDocumentoDto request) throws BadRequestException {
    TiposDocumento tiposDocumento = TiposDocumento.builder()
        .nombre(request.getNombre())
        .abreviatura(request.getAbreviatura())
        .build();
    tiposDocumentoRepository.save(tiposDocumento);
    return modelMapper.map(tiposDocumento, TiposDocumentoDto.class);
  }

  @Override
  public TiposDocumentoDto update(Integer id, TiposDocumentoDto request) throws BadRequestException {
    TiposDocumento tiposDocumento = this.findTiposDocumentoById(id);
    tiposDocumento.setNombre(request.getNombre());
    tiposDocumento.setAbreviatura(request.getAbreviatura());
    return modelMapper.map(tiposDocumento, TiposDocumentoDto.class);
  }

  @Override
  public ResponseEntity<Void> delete(Integer id) throws BadRequestException {
    if (tiposDocumentoRepository.existsById(id)) {
      TiposDocumento tiposDocumento = this.findTiposDocumentoById(id);
      tiposDocumentoRepository.delete(tiposDocumento);
      return ResponseEntity.noContent().build();
    }
    throw new BadRequestException("No existe tipo documento con ese id");
  }

  private TiposDocumento findTiposDocumentoById(Integer id) throws BadRequestException {
    return tiposDocumentoRepository.findById(id)
        .orElseThrow(
            () -> new BadRequestException("No existe tipo documento con ese id"));
  }
}
