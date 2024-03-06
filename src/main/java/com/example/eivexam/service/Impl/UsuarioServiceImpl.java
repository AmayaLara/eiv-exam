package com.example.eivexam.service.Impl;

import com.example.eivexam.model.TiposDocumento;
import com.example.eivexam.model.Usuario;
import com.example.eivexam.repository.UsuarioRepository;
import com.example.eivexam.service.PersonaService;
import com.example.eivexam.service.TiposDocumentoService;
import com.example.eivexam.service.UsuarioService;
import com.example.eivexam.utils.ClaveCompuestaPersona;
import com.example.eivexam.utils.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final ModelMapper modelMapper;
  private final PersonaService personaService;
  private final TiposDocumentoService tiposDocumentoService;

  @Override
  public List<UsuarioDto> getAll() {
    return usuarioRepository.findAll()
        .stream().map(usuario ->
            modelMapper.map(usuario, UsuarioDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public UsuarioDto getById(Integer id, String abreviatura) throws BadRequestException {
    TiposDocumento tiposDocumento = tiposDocumentoService.findTipoDocumentoByAbreviatura(abreviatura);
    return modelMapper.map(this.findUsuarioById(
            new ClaveCompuestaPersona(id, tiposDocumento)),
        UsuarioDto.class);
  }

  @Override
  public UsuarioDto update(Integer numeroDocumento, String abreviatura, UsuarioDto request) throws BadRequestException {
    TiposDocumento tiposDocumento = tiposDocumentoService.findTipoDocumentoByAbreviatura(abreviatura);

    Usuario usuarioById = this.findUsuarioById(new ClaveCompuestaPersona(numeroDocumento, tiposDocumento));
    usuarioRepository.findByUsername(request.getUsername())
        .ifPresent(user -> {
          if (user != usuarioById) {
            try {
              throw new BadRequestException("Ya existe Usuario con ese username");
            } catch (BadRequestException e) {
              throw new RuntimeException(e);
            }
          }
        });
    usuarioById.setUsername(request.getUsername());
    usuarioById.setRol(request.getRol().name());
    return modelMapper.map(usuarioById, UsuarioDto.class);
  }

  @Override
  public ResponseEntity<Void> delete(Integer numeroDocumento, String abreviatura) throws BadRequestException {
    TiposDocumento tiposDocumento = tiposDocumentoService.findTipoDocumentoByAbreviatura(abreviatura);
    Usuario usuario = this.findUsuarioById(new ClaveCompuestaPersona(numeroDocumento, tiposDocumento));
    usuarioRepository.delete(usuario);
    personaService.delete(numeroDocumento, abreviatura);
    return ResponseEntity.noContent().build();
  }

  private Usuario findUsuarioById(ClaveCompuestaPersona id) throws BadRequestException {
    return usuarioRepository.findById(id)
        .orElseThrow(
            () -> new BadRequestException("No existe Usuario con ese id"));
  }


}
