package com.eco.projetoeco.presentation.dto;

import com.eco.projetoeco.domain.model.enuns.DiaSemana;
import com.eco.projetoeco.domain.model.enuns.Turno;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HorariosColetaDTO {

    @Schema(description = "Identificador do horário", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Dia da semana")
    @NotNull(message = "Dia da semana é obrigatório")
    private DiaSemana diaSemana;

    @Schema(description = "Turno")
    @NotNull(message = "Turno é obrigatório")
    private Turno turno;

    @Schema(description = "CEP do endereço", example = "65000000")
    @Pattern(regexp = "\\d{8}", message = "CEP deve ter 8 dígitos numéricos")
    private String enderecoCep;
}
