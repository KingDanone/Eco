package com.eco.projetoeco.data.repository;


import com.eco.projetoeco.data.model.Usuario;
import com.eco.projetoeco.data.model.enuns.UserRole;
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

