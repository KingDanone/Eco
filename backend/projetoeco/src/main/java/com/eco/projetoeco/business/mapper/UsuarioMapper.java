package com.eco.projetoeco.business.mapper;

import com.eco.projetoeco.domain.model.Usuario;
import com.eco.projetoeco.presentation.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "denuncias", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> toDTO(List<Usuario> usuarios);
}
