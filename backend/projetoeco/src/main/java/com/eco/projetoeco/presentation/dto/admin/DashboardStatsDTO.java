package com.eco.projetoeco.presentation.dto.admin;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardStatsDTO {

    @Schema(description = "Número total de usuários cadastrados no sistema.", example = "1247")
    private long totalUsuarios;

    @Schema(description = "Número total de denúncias já feitas.", example = "89")
    private long totalDenuncias;

    @Schema(description = "Número de denúncias com status ABERTA.", example = "23")
    private long denunciasAbertas;

    @Schema(description = "Número de denúncias com status EM_ANALISE.", example = "18")
    private long denunciasEmAnalise;

    @Schema(description = "Número de denúncias com status FECHADA.", example = "66")
    private long denunciasFechadas;
}