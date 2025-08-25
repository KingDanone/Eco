package com.eco.projetoeco.service;

import com.eco.projetoeco.dto.UsuarioDto;
import com.eco.projetoeco.dto.UsuarioRequestDto;
import com.eco.projetoeco.dto.UsuarioSenhaUpdateDto;
import com.eco.projetoeco.dto.UsuarioUpdateRequestDto;
import jakarta.validation.Valid;

import java.util.List;

public interface UsuarioService {

    UsuarioDto criar(UsuarioRequestDto dto);
    List<UsuarioDto> listarTodos();
    UsuarioDto buscarPorCpf(String cpf);
    UsuarioDto autenticar(String cpf, String senha);
    UsuarioDto editar(String cpf, @Valid UsuarioUpdateRequestDto dto);
    void alterarSenha(String cpf, UsuarioSenhaUpdateDto dto);

}
