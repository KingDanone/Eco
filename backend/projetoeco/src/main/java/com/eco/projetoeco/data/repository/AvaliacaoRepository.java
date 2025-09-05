package com.eco.projetoeco.data.repository;

import com.eco.projetoeco.data.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
