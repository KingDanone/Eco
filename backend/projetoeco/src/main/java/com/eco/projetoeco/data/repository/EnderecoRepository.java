package com.eco.projetoeco.data.repository;

import com.eco.projetoeco.data.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
    Optional<Endereco> findByCep(String cep);
    
    boolean existsByCep(String cep);
    
    void deleteByCep(String cep);
}
