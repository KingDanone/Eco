package com.eco.projetoeco.domain.repository;


import com.eco.projetoeco.domain.model.Usuario;
import com.eco.projetoeco.domain.model.enuns.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCpf(String cpf);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByEmailOrCpf(String email, String cpf);
    boolean existsByRole(UserRole role);
}

