package com.eco.projetoeco.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaResumoDto {
    private Long id;
    private String titulo;
    private String descricao;
}
