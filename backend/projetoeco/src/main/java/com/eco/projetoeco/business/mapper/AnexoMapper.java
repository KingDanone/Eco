package com.eco.projetoeco.business.mapper;

import com.eco.projetoeco.domain.model.Anexo;
import com.eco.projetoeco.presentation.dto.AnexoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnexoMapper {

    AnexoMapper INSTANCE = Mappers.getMapper(AnexoMapper.class);

    @Mapping(target = "urlAcesso", expression = "java(\"/api/files/denuncias/\" + anexo.getCaminhoArmazenado())")
    AnexoDTO toDTO(Anexo anexo);

    @Mapping(target = "dataUpload", ignore = true) // Data de upload será gerada na entidade Anexo
    @Mapping(target = "denuncia", ignore = true) // Denuncia será associada no serviço
    @Mapping(target = "id", ignore = true) // ID será gerado automaticamente
    @Mapping(target = "nomeArmazenado", ignore = true) // Nome armazenado será gerado no serviço
    @Mapping(target = "contentType", ignore = true) // Content type será detectado/salvo no serviço
    @Mapping(target = "tamanhoBytes", ignore = true) // Tamanho será calculado/salvo no serviço
    @Mapping(target = "caminhoArmazenado", ignore = true) // Caminho de armazenamento será gerado no serviço
    Anexo toEntity(AnexoDTO anexoDTO);
}
