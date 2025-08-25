package com.eco.projetoeco.dto;

import com.eco.projetoeco.model.StatusAtendimento;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoDto {
    private Long protocolo;
    private LocalDate dataAtendimento;
    private StatusAtendimento status;
    private FuncionarioResumoDto funcionario;
    private DenunciaResumoDto denuncia;
}
