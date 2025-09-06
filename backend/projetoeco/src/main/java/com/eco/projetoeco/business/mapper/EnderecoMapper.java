package com.eco.projetoeco.business.mapper;

import com.eco.projetoeco.data.model.Endereco;
import com.eco.projetoeco.presentation.dto.EnderecoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    EnderecoDTO toDTO(Endereco endereco);

    Endereco toEntity(EnderecoDTO enderecoDTO);
}
