package com.eco.projetoeco.presentation.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespostaDTO {

    @Schema(description = "identificador de Resposta", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Mensagem é obrigatória")
    @Schema(description = "Mensagem de resposta da denúncia")
    private String mensagem;

    @Schema(description = "Data da resposta", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dataResposta;

    @Schema(description = "ID da denúncia a qual a resposta se refere")
    private Long denunciaId;

}
