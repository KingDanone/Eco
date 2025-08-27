package com.eco.projetoeco.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank
    private String identifier; // cpf ou email

    @NotBlank
    private String senha;
}
