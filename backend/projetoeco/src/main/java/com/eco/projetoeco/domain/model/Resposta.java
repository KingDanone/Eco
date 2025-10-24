package com.eco.projetoeco.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "resposta")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mensagem", nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "denuncia_id", nullable = false)
    private Denuncia denuncia;

    @CreationTimestamp // data_resposta TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
    @Column(name = "data_resposta", updatable = false)
    private LocalDateTime dataResposta;
}
