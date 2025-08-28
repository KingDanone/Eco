package com.eco.projetoeco.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class EnderecoDTO {

    @Schema(description = "CEP do endereço (8 dígitos)", example = "65000000")
    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "CEP deve ter 8 dígitos numéricos")
    private String cep;

    @Schema(description = "Estado", example = "MA")
    @NotBlank(message = "Estado é obrigatório")
    @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres")
    private String estado;

    @Schema(description = "Cidade", example = "São Luís")
    @NotBlank(message = "Cidade é obrigatória")
    @Size(min = 2, max = 100, message = "Cidade deve ter entre 2 e 100 caracteres")
    private String cidade;

    @Schema(description = "Bairro", example = "Centro")
    @NotBlank(message = "Bairro é obrigatório")
    @Size(min = 2, max = 100, message = "Bairro deve ter entre 2 e 100 caracteres")
    private String bairro;

    @Schema(description = "Logradouro", example = "Rua A, 123")
    @NotBlank(message = "Logradouro é obrigatório")
    @Size(min = 2, max = 150, message = "Logradouro deve ter entre 2 e 150 caracteres")
    private String logradouro;
}
