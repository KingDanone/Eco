package com.eco.projetoeco.presentation.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DenunciaDto {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private UsuarioDTO usuario;
    private EnderecoResumoDto endereco;
}
