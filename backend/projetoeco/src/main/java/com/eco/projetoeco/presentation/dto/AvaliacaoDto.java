package com.eco.projetoeco.presentation.dto;

import com.eco.projetoeco.data.model.NivelAvaliacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDto {
    private Long id;
    private String cpfUsuario;
    private Long idJogo;
    private NivelAvaliacao nivelAvaliacao;
    private String comentario;
    private LocalDateTime dataCriacao;
}
