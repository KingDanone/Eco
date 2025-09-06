package com.eco.projetoeco.data.repository;


import com.eco.projetoeco.data.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    @Query("SELECT d FROM Denuncia d JOIN FETCH d.usuario JOIN FETCH d.endereco")
    List<Denuncia> findAllWithAssociations();
}
