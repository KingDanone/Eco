package com.eco.projetoeco.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO para mudança de senha
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSenhaUpdateDto {
    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;
}

