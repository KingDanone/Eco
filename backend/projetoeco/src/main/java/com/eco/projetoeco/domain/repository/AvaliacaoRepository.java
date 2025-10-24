package com.eco.projetoeco.domain.repository;

import com.eco.projetoeco.domain.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
