package com.eco.projetoeco.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateRequestDto {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Nick é obrigatório")
    private String nickname;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
    private String telefone;

    // senha não presente aqui, ou opcional se quiser permitir alterar
}

