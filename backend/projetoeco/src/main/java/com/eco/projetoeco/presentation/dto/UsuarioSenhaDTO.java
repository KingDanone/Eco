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

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioSenhaDTO {

    @Schema(description = "Senha atual do usuário.", example = "Senha@Atual123")
    @NotBlank(message = "Senha atual é obrigatória")
    @Size(min = 6, max = 100, message = "Senha atual deve ter entre 6 e 100 caracteres")
    private String currentPassword;

    @Schema(description = "Nova senha do usuário.", example = "NovaSenha@Forte123")
    @NotBlank(message = "Nova senha é obrigatória")
    @Size(min = 6, max = 100, message = "Nova senha deve ter entre 6 e 100 caracteres")
    private String newPassword;
}


