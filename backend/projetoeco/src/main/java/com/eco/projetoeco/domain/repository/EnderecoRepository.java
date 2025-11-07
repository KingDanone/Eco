package com.eco.projetoeco.domain.repository;

import com.eco.projetoeco.domain.model.Endereco;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
    Optional<Endereco> findByCep(String cep);
    
    boolean existsByCep(String cep);

    @Transactional
    void deleteByCep(String cep);
}
