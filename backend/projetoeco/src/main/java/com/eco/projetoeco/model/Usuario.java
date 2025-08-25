package com.eco.projetoeco.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nickname"})
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {

    @Id
    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "nickname", nullable = false, length = 65)
    private String nickname;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "telefone", length = 15)
    private String telefone;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    // Um Usuario pode ter muitas Denuncias
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Denuncia> denuncias = new ArrayList<>();

    // Um Usuario pode ter muitas Avaliacoes
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Avaliacao> avaliacoes = new ArrayList<>();
}
