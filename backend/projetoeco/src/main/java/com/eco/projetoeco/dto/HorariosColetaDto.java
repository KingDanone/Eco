package com.eco.projetoeco.dto;

import com.eco.projetoeco.model.DiaSemana;
import com.eco.projetoeco.model.Turno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorariosColetaDto {
    private Long id;
    private DiaSemana diaSemana;
    private Turno turno;
    private String enderecoCep;
}
