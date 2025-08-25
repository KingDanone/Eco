package com.eco.projetoeco.service;

import com.eco.projetoeco.dto.EnderecoDto;
import com.eco.projetoeco.dto.EnderecoRequestDto;

import java.util.List;

public interface EnderecoService {
    EnderecoDto criarEndereco(EnderecoRequestDto dto);
    List<EnderecoDto> listarTodos();
    void deletarPorCep(String cep);
}
