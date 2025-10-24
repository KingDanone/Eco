package com.eco.projetoeco.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "anexo")
@NoArgsConstructor
@AllArgsConstructor
public class Anexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_original", nullable = false, length = 255)
    private String nomeOriginal;

    @Column(name = "nome_armazenado", nullable = false, length = 255)
    private String nomeArmazenado;

    @Column(name = "caminho_armazenado", nullable = false, length = 500)
    private String caminhoArmazenado;

    @Column(name = "content_type", nullable = false, length = 255)
    private String contentType;

    @Column(name = "tamanho_bytes", nullable = false)
    private Long tamanhoBytes;

    @CreationTimestamp
    @Column(name = "data_upload", updatable = false)
    private LocalDateTime dataUpload;

    @OneToOne(mappedBy = "anexo", fetch = FetchType.LAZY)
    private Denuncia denuncia;
}
