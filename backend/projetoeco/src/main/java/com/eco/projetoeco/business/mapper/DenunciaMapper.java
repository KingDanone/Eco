package com.eco.projetoeco.business.mapper;

import com.eco.projetoeco.data.model.Denuncia;
import com.eco.projetoeco.presentation.dto.DenunciaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, EnderecoMapper.class})
public interface DenunciaMapper {

    DenunciaMapper INSTANCE = Mappers.getMapper(DenunciaMapper.class);

    DenunciaDTO toDTO(Denuncia denuncia);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    Denuncia toEntity(DenunciaDTO denunciaDTO);

    List<DenunciaDTO> toDTO(List<Denuncia> denuncias);
}
