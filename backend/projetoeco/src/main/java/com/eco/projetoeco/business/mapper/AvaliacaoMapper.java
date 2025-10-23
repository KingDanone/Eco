package com.eco.projetoeco.business.mapper;

import com.eco.projetoeco.data.model.Avaliacao;
import com.eco.projetoeco.presentation.dto.AvaliacaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {

    AvaliacaoMapper INSTANCE = Mappers.getMapper(AvaliacaoMapper.class);

    @Mapping(source = "usuario.cpf", target = "cpfUsuario")
    @Mapping(source = "jogo.id", target = "idJogo")
    AvaliacaoDTO toDTO(Avaliacao avaliacao);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "jogo", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    Avaliacao toEntity(AvaliacaoDTO avaliacaoDTO);

    List<AvaliacaoDTO> toDTO(List<Avaliacao> avaliacoes);
}
