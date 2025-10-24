package com.eco.projetoeco.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "endereco")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cep", length = 8, nullable = false)
    private String cep;

    @Column(name = "estado", nullable = false, length = 45)
    private String estado;

    @Column(name = "cidade", nullable = false, length = 45)
    private String cidade;

    @Column(name = "bairro", nullable = false, length = 45)
    private String bairro;

    @Column(name = "logradouro", nullable = false, length = 100)
    private String logradouro;

    @OneToMany(mappedBy = "endereco",cascade = CascadeType.ALL,
             orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Denuncia> denuncias = new ArrayList<>();

    @OneToMany(mappedBy = "endereco", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HorariosColeta> horariosColetas = new ArrayList<>();
}
