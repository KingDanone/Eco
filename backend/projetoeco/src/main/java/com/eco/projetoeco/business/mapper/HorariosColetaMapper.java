package com.eco.projetoeco.business.mapper;

import com.eco.projetoeco.domain.model.HorariosColeta;
import com.eco.projetoeco.presentation.dto.HorariosColetaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HorariosColetaMapper {

    HorariosColetaMapper INSTANCE = Mappers.getMapper(HorariosColetaMapper.class);

    @Mapping(source = "endereco.cep", target = "enderecoCep")
    HorariosColetaDTO toDTO(HorariosColeta horariosColeta);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    HorariosColeta toEntity(HorariosColetaDTO horariosColetaDTO);

    List<HorariosColetaDTO> toDTO(List<HorariosColeta> horariosColetas);
}
