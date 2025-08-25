package com.eco.projetoeco.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcionario", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),    // email é único
        @UniqueConstraint(columnNames = {"telefone"})
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Funcionario {

    @Id
    @Column(name = "codigo", length = 6)
    private String codigo;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "telefone", nullable = false, length = 15)
    private String telefone;
    
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.LAZY)
    private List<Atendimento> atendimentos = new ArrayList<>();
}
