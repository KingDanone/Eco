package com.eco.projetoeco.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioResumoDto {
    private String codigo;
    private String nome;
    private String email;
    private String telefone;
}
