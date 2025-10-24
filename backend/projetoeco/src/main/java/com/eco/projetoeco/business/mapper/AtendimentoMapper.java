package com.eco.projetoeco.business.mapper;

import com.eco.projetoeco.domain.model.Atendimento;
import com.eco.projetoeco.presentation.dto.AtendimentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DenunciaMapper.class, RespostaMapper.class})
public interface AtendimentoMapper {

    AtendimentoMapper INSTANCE = Mappers.getMapper(AtendimentoMapper.class);

    AtendimentoDTO toDTO(Atendimento atendimento);

    @Mapping(target = "protocolo", ignore = true)
    @Mapping(target = "status", ignore = true)
    Atendimento toEntity(AtendimentoDTO atendimentoDTO);

    List<AtendimentoDTO> toDTO(List<Atendimento> atendimentos);
}
