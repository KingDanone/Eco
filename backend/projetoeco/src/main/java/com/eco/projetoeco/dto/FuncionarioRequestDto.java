package com.eco.projetoeco.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioRequestDto {

    @NotBlank(message = "Código é obrigatório!")
    @Pattern(regexp = "\\d{6}", message = "Código deve ter 6 dígitos numéricos!")
    private String codigo;

    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @NotBlank(message = "Email é obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    @NotBlank(message = "Telefone é obrigatório!")
    @Pattern(regexp = "\\d{10,15}", message = "Telefone deve conter entre 10 e 15 dígitos!")
    private String telefone;

    @NotBlank(message = "Senha é obrigatória!")
    private String senha;
}
