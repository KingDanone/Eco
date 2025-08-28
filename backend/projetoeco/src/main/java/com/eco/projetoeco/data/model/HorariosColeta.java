package com.eco.projetoeco.data.model;


import com.eco.projetoeco.data.model.enuns.DiaSemana;
import com.eco.projetoeco.data.model.enuns.Turno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "horarios_coleta")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HorariosColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", length = 20)
    private DiaSemana diaSemana;

    @Enumerated(EnumType.STRING)
    @Column(name = "turno", length = 20)
    private Turno turno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "endereco_cep", nullable = false)
    private Endereco endereco;
}
