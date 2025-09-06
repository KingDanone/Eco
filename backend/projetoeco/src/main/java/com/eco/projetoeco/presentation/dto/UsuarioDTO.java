package com.eco.projetoeco.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
public class UsuarioDTO {

    @Schema(description = "ID do usuário.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(description = "CPF do usuário contendo exatamente 11 dígitos numéricos.", example = "62617565033")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
    private String cpf;

    @Schema(description = "Nome completo do usuário.", example = "Maria Silva")
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Schema(description = "Apelido do usuário.", example = "maria_s")
    @NotBlank(message = "Nick é obrigatório")
    private String nickname;

    @Schema(type = "string", format = "email", description = "E-mail do usuário.", example = "maria.silva@example.com")
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @Schema(description = "Telefone do usuário (somente números, com até 15 dígitos).", example = "98987654321")
    @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
    @Pattern(regexp = "^\\d{8,15}$", message = "Telefone deve conter entre 8 e 15 dígitos numéricos")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String telefone;

    @Schema(description = "Senha do usuário (apenas escrita).", example = "NovaSenha@Forte123", accessMode = Schema.AccessMode.WRITE_ONLY)
    @Size(min = 6, max = 100, message = "Senha deve ter entre 6 e 100 caracteres")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
}


