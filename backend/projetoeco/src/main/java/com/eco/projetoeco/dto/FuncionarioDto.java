package com.eco.projetoeco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDto {
    private String codigo;
    private String nome;
    private String email;
    private String telefone;
}
