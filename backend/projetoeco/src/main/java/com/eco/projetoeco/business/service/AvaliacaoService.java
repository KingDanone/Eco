package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.AvaliacaoDTO;

import java.util.List;

public interface AvaliacaoService {
    AvaliacaoDTO criar(AvaliacaoDTO dto);
    List<AvaliacaoDTO> listarTodos();
    void deletar(Long id);
}
