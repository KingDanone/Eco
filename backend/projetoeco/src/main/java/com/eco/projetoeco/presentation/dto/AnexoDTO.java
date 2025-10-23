package com.eco.projetoeco.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnexoDTO {

    @Schema(description = "Identificador do anexo", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome do arquivo original", example = "imagem.jpg")
    private String nomeOriginal;

    @Schema(description = "URL para acesso ao anexo", example = "/files/denuncias/denuncia_1_foto_lixo.jpg")
    private String urlAcesso;

    @Schema(description = "Tipo de conteúdo do arquivo", example = "image/jpeg")
    private String contentType;

    @Schema(description = "Tamanho do arquivo em bytes", example = "1024000")
    private Long tamanhoBytes;

    @Schema(description = "Data de upload do arquivo", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dataUpload;
}
