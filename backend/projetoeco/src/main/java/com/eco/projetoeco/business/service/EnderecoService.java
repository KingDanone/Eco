package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.EnderecoDTO;

import java.util.List;

public interface EnderecoService {
    EnderecoDTO criarEndereco(EnderecoDTO dto);
    List<EnderecoDTO> listarTodos();
    void deletarPorCep(String cep);
}
