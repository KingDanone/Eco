package com.eco.projetoeco.dto;

import com.eco.projetoeco.model.NivelAvaliacao;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AvaliacaoRequestDto {

    @NotNull
    private String cpfUsuario;

    @NotNull
    private Long idJogo;

    @NotNull
    private NivelAvaliacao nivelAvaliacao;

    private String comentario;
}
