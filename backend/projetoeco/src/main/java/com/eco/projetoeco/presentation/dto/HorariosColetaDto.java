package com.eco.projetoeco.presentation.dto;

import com.eco.projetoeco.data.model.DiaSemana;
import com.eco.projetoeco.data.model.Turno;
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
