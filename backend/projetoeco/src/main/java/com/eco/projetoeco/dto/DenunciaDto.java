package com.eco.projetoeco.dto;

import com.eco.projetoeco.model.Endereco;
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
    private UsuarioResumoDto usuario;
    private EnderecoResumoDto endereco;
}
