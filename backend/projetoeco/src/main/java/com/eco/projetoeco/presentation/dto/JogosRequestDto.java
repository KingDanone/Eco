package com.eco.projetoeco.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JogosRequestDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String genero;

    private String descricao;
    private LocalDate dataLancamento;
    private String desenvolvedor;
    private String linkJogo;
}
