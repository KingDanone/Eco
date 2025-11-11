package com.eco.projetoeco.presentation.dto.denunciadto;

import com.eco.projetoeco.domain.model.enuns.StatusDenuncia;
import com.eco.projetoeco.presentation.dto.AnexoDTO;
import com.eco.projetoeco.presentation.dto.EnderecoDTO;
import com.eco.projetoeco.presentation.dto.RespostaDTO;
import com.eco.projetoeco.presentation.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
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
    @Size(min = 3, max = 255, message = "Título deve ter entre 3 e 255 caracteres")
    private String titulo;

    @Schema(description = "Descrição da denúncia.", example = "Há lixo acumulado há semanas na praça central.")
    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 3, max = 1000, message = "Descrição deve ter entre 3 e 1000 caracteres")
    private String descricao;

    @Schema(description = "Data de criação", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dataCriacao;

    @Schema(description = "Data de atualização", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dataAtualizacao;

    @Schema(description = "Status atual da denúncia", accessMode = Schema.AccessMode.READ_ONLY)
    private StatusDenuncia status;

    @Schema(description = "Usuário autor da denúncia", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UsuarioDTO usuario;

    @Schema(description = "Endereço relacionado à denúncia")
    @NotNull(message = "Endereço é obrigatório")
    @Valid
    private EnderecoDTO endereco;

    @Schema(description = "Respostas associadas à denúncia", accessMode = Schema.AccessMode.READ_ONLY)
    private List<RespostaDTO> respostas;

    @Schema(description = "Anexo da denúncia", accessMode = Schema.AccessMode.READ_ONLY)
    private AnexoDTO anexo;
}
