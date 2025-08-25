package com.eco.projetoeco.repository;


import com.eco.projetoeco.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByCpf(String cpf);
    Usuario findByEmail(String email);
}

