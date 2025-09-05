package com.eco.projetoeco.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class JogosDTO {

    @Schema(description = "Identificador do jogo", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome do jogo", example = "EcoAdventure")
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 150, message = "Nome deve ter entre 2 e 150 caracteres")
    private String nome;

    @Schema(description = "Gênero do jogo", example = "Aventura")
    @NotBlank(message = "Gênero é obrigatório")
    @Size(min = 2, max = 100, message = "Gênero deve ter entre 2 e 100 caracteres")
    private String genero;

    @Schema(description = "Descrição do jogo", example = "Jogo educativo de sustentabilidade")
    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    private String descricao;

    @Schema(description = "Data de lançamento")
    private LocalDate dataLancamento;

    @Schema(description = "Desenvolvedor", example = "Eco Studio")
    @Size(max = 100, message = "Desenvolvedor deve ter no máximo 100 caracteres")
    private String desenvolvedor;

    @Schema(description = "Link do jogo", example = "https://exemplo.com/jogo")
    @Size(max = 255, message = "Link deve ter no máximo 255 caracteres")
    private String linkJogo;
}
