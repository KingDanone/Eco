package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.UsuarioDTO;
import com.eco.projetoeco.presentation.dto.UsuarioSenhaDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO criar(@Valid UsuarioDTO dto);
    List<UsuarioDTO> listarTodos();
    UsuarioDTO buscarPorCpf(String cpf);
    UsuarioDTO autenticar(String cpf, String senha);
    UsuarioDTO editar(String cpf, @Valid UsuarioDTO dto);
    void alterarSenha(String cpf, @Valid UsuarioSenhaDTO dto);

}
