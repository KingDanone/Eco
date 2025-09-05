package com.eco.projetoeco.data.model.enuns;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Turno {
    MATUTINO,
    VESPERTINO,
    NOTURNO
}
