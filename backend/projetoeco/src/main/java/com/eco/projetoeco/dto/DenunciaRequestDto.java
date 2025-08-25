package com.eco.projetoeco.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DenunciaRequestDto {

    @NotBlank(message = "Título é obrigatório!")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória!")
    private String descricao;

    @NotBlank(message = "CPF do usuário é obrigatorio")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos numéricos!")
    private String usuarioCpf;

    @NotBlank(message = "CEP do endereço é obrigatorio")
    @Pattern(regexp = "\\d{8}", message = "CPF deve ter 11 dígitos numéricos!")
    private String enderecoCep;
}
