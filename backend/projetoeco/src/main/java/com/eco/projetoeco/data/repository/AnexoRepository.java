package com.eco.projetoeco.data.repository;

import com.eco.projetoeco.data.model.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Long> {
}
