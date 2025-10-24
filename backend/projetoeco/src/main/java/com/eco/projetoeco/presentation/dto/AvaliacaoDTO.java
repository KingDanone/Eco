package com.eco.projetoeco.presentation.dto;

import com.eco.projetoeco.domain.model.enuns.NivelAvaliacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvaliacaoDTO {

    @Schema(description = "Identificador da avaliação", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "CPF do usuário autor", example = "62617565033")
    @NotBlank(message = "CPF do usuário é obrigatório")
    private String cpfUsuario;

    @Schema(description = "Identificador do jogo", example = "1")
    @NotNull(message = "ID do jogo é obrigatório")
    private Long idJogo;

    @Schema(description = "Nível de avaliação")
    @NotNull(message = "Nível de avaliação é obrigatório")
    private NivelAvaliacao nivelAvaliacao;

    @Schema(description = "Comentário opcional", example = "Jogo muito bom!")
    @Size(max = 500, message = "Comentário deve ter no máximo 500 caracteres")
    private String comentario;

    @Schema(description = "Data de criação", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dataCriacao;
}
