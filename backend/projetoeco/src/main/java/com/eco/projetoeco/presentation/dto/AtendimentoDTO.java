package com.eco.projetoeco.presentation.dto;

import com.eco.projetoeco.data.model.enuns.StatusAtendimento;
import com.eco.projetoeco.presentation.dto.denunciadto.DenunciaDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtendimentoDTO {

    @Schema(description = "Protocolo do atendimento", accessMode = Schema.AccessMode.READ_ONLY)
    private Long protocolo;

    @Schema(description = "Data do atendimento", example = "2025-01-31")
    @NotNull(message = "Data de atendimento é obrigatória")
    private LocalDate dataAtendimento;

    @Schema(description = "Status do atendimento")
    private StatusAtendimento status;

    @Schema(description = "ID da denúncia a ser vinculada. Usado para criar um novo atendimento.", example = "1")
    @NotNull(message = "ID da Denúncia é obrigatório")
    private Long denunciaId;

    @Schema(description = "Denúncia vinculada", accessMode = Schema.AccessMode.READ_ONLY)
    private DenunciaDTO denuncia;

    }
