package com.eco.projetoeco.domain.repository;

import com.eco.projetoeco.domain.model.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Long> {
    Optional<Anexo> findByCaminhoArmazenado(String caminhoArmazenado);
}
