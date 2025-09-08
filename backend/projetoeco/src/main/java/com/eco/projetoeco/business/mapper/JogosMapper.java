package com.eco.projetoeco.business.mapper;

import com.eco.projetoeco.data.model.Jogos;
import com.eco.projetoeco.presentation.dto.JogosDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JogosMapper {

    JogosMapper INSTANCE = Mappers.getMapper(JogosMapper.class);

    JogosDTO toDTO(Jogos jogos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "avaliacoes", ignore = true)
    Jogos toEntity(JogosDTO jogosDTO);

    List<JogosDTO> toDTO(List<Jogos> jogos);
}
