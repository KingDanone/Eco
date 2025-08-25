package com.eco.projetoeco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResumoDto {
    private String cpf;
    private String nome;
    private String nickname;
    private String email;
    private String telefone;
}

