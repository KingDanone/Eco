package com.eco.projetoeco.presentation.dto;

import com.eco.projetoeco.data.model.StatusAtendimento;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoDto {
    private Long protocolo;
    private LocalDate dataAtendimento;
    private StatusAtendimento status;
    private UsuarioDTO usuario;
    private DenunciaResumoDto denuncia;
}
