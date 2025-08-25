package com.eco.projetoeco.repository;

import com.eco.projetoeco.model.HorariosColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorariosColetaRepository extends JpaRepository<HorariosColeta, Long> {
}
