package com.eco.projetoeco.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class JogosDto {

    private Long id;
    private String nome;
    private String genero;
    private String descricao;
    private LocalDate dataLancamento;
    private String desenvolvedor;
    private String linkJogo;

    public JogosDto(Long id, String nome, String genero, String descricao, LocalDate dataLancamento, String desenvolvedor, String linkJogo) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.descricao = descricao;
        this.dataLancamento = dataLancamento;
        this.desenvolvedor = desenvolvedor;
        this.linkJogo = linkJogo;
    }
}
