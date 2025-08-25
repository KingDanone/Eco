package com.eco.projetoeco.dto;

import com.eco.projetoeco.model.DiaSemana;
import com.eco.projetoeco.model.Turno;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorariosColetaRequestDto {

    @NotNull(message = "Dia da semana é obrigatório")
    private DiaSemana diaSemana;

    @NotNull(message = "Turno é obrigatório")
    private Turno turno;

    @Pattern(regexp = "\\d{8}", message = "CEP deve ter 8 dígitos numéricos")
    private String enderecoCep;
}
