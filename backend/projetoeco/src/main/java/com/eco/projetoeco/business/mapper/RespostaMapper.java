package com.eco.projetoeco.business.mapper;

import com.eco.projetoeco.data.model.Resposta;
import com.eco.projetoeco.presentation.dto.RespostaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RespostaMapper {

    RespostaMapper INSTANCE = Mappers.getMapper(RespostaMapper.class);

    @Mapping(source = "atendimento.protocolo", target = "atendimentoId")
    RespostaDTO toDTO(Resposta resposta);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "atendimento", ignore = true)
    @Mapping(target = "dataResposta", ignore = true)
    Resposta toEntity(RespostaDTO respostaDTO);

    List<RespostaDTO> toDTO(List<Resposta> respostas);
}
