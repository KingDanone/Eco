package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.EnderecoDto;
import com.eco.projetoeco.presentation.dto.EnderecoRequestDto;

import java.util.List;

public interface EnderecoService {
    EnderecoDto criarEndereco(EnderecoRequestDto dto);
    List<EnderecoDto> listarTodos();
    void deletarPorCep(String cep);
}
