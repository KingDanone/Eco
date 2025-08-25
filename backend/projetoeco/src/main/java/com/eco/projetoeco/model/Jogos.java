package com.eco.projetoeco.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jogos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome"})
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Jogos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "genero", nullable = false, length = 45)
    private String genero;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @Column(name = "desenvolvedor", length = 100)
    private String desenvolvedor;

    @Column(name = "link_jogo", length = 255)
    private String linkJogo;

    @OneToMany(mappedBy = "jogo", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Avaliacao> avaliacoes = new ArrayList<>();

}
