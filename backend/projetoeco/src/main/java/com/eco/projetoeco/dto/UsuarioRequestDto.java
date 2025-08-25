package com.eco.projetoeco.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDto {

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
    private String cpf;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Nick é obrigatório")
    private String nickname;

    @NotBlank(message = "email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @Size(max = 15, message = "Telefonedeve ter no máximo 15 caracteres")
    private String telefone;

    @NotBlank(message = "Senha é obrigatório")
    private String senha;
}
