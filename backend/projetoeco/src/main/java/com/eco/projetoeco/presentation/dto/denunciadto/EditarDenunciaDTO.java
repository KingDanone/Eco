package com.eco.projetoeco.presentation.dto.denunciadto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarDenunciaDTO {

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 3, max = 150, message = "Título deve ter entre 3 e 150 caracteres")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 3, max = 1000, message = "Descrição deve ter entre 3 e 1000 caracteres")
    private String descricao;
}
