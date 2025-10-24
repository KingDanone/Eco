package com.eco.projetoeco.domain.model.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NivelAvaliacao {
    UM("1"),
    DOIS("2"),
    TRES("3"),
    QUATRO("4"),
    CINCO("5");

    private final String valor;

    NivelAvaliacao(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return valor;
    }

    @JsonCreator
    public static NivelAvaliacao fromValor(String valor) {
        for (NivelAvaliacao nivel : NivelAvaliacao.values()) {
            if (nivel.valor.equals(valor)) {
                return nivel;
            }
        }
        throw new IllegalArgumentException("Valor da avaliação inválida: " + valor);
    }
}
