package com.eco.projetoeco.presentation.dto.denunciadto;

import com.eco.projetoeco.domain.model.enuns.StatusDenuncia;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateDenunciaStatusDTO {

    @NotNull(message = "O novo status não pode ser nulo.")
    private StatusDenuncia status;
}
