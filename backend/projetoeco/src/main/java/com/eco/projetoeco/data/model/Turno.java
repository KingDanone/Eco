package com.eco.projetoeco.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Turno {
    MATUTINO,
    VESPERTINO,
    NOTURNO
}
