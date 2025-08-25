package com.eco.projetoeco.repository;

import com.eco.projetoeco.model.Jogos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogosRepository extends JpaRepository<Jogos, Long> {
}
