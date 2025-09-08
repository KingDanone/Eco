package com.eco.projetoeco.data.repository;


import com.eco.projetoeco.data.model.Denuncia;
import com.eco.projetoeco.data.model.enuns.StatusDenuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    @Query("SELECT DISTINCT d FROM Denuncia d JOIN FETCH d.usuario JOIN FETCH d.endereco LEFT JOIN FETCH d.respostas")
    List<Denuncia> findAllWithAssociations();

    @Query("SELECT DISTINCT d FROM Denuncia d JOIN FETCH d.usuario JOIN FETCH d.endereco LEFT JOIN FETCH d.respostas WHERE d.status = :status")
    List<Denuncia> findAllByStatusWithAssociations(StatusDenuncia status);
}
