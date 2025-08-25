package com.eco.projetoeco.model;


import com.eco.projetoeco.converter.NivelAvaliacaoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacoes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_cpf", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jogos_id", nullable = false)
    private Jogos jogo;

    @Convert(converter = NivelAvaliacaoConverter.class)
    @Column(name = "avaliacao", nullable = false)
    private NivelAvaliacao nivelAvaliacao;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;
}
