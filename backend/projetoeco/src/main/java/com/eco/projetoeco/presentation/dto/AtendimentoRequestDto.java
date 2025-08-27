package com.eco.projetoeco.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoRequestDto {

    @NotNull(message = "Data de atendimento é obrigatória")
    private LocalDate dataAtendimento;

    @NotNull(message = "ID do usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "ID da denúncia é obrigatório")
    private Long denunciaId;
}
