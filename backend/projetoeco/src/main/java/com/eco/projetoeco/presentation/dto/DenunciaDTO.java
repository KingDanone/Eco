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

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DenunciaDTO {

    @Schema(description = "Identificador da denúncia", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Título da denúncia.", example = "Lixo acumulado na praça")
    @NotBlank(message = "Título é obrigatório")
    @Size(min = 3, max = 150, message = "Título deve ter entre 3 e 150 caracteres")
    private String titulo;

    @Schema(description = "Descrição da denúncia.", example = "Há lixo acumulado há semanas na praça central.")
    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 3, max = 1000, message = "Descrição deve ter entre 3 e 1000 caracteres")
    private String descricao;

    @Schema(description = "Data de criação", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dataCriacao;

    @Schema(description = "Data de atualização", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dataAtualizacao;

    @Schema(description = "Usuário autor da denúncia")
    private UsuarioDTO usuario;

    @Schema(description = "Endereço relacionado à denúncia")
    private EnderecoDTO endereco;
}
