package com.eco.projetoeco.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "atendimento")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "protocolo")
    private Long protocolo;

    @Column(name = "data_atendimento", nullable = false)
    private LocalDate dataAtendimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusAtendimento status = StatusAtendimento.ABERTO;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "funcionario_codigo", nullable = false)
    private Funcionario funcionario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "denuncia_id", nullable = false)
    private Denuncia denuncia;

    @OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Resposta> respostas = new ArrayList<>();
}