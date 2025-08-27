package com.eco.projetoeco.data.repository;

import com.eco.projetoeco.data.model.Jogos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogosRepository extends JpaRepository<Jogos, Long> {
}
