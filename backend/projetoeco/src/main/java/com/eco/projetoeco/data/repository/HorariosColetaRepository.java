package com.eco.projetoeco.data.repository;

import com.eco.projetoeco.data.model.HorariosColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorariosColetaRepository extends JpaRepository<HorariosColeta, Long> {
}
