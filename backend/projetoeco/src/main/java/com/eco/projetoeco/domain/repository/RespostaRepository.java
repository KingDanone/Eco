package com.eco.projetoeco.domain.repository;

import com.eco.projetoeco.domain.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {}
