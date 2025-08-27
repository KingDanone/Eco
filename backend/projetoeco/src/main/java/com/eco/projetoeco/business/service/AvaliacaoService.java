package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.AvaliacaoDto;
import com.eco.projetoeco.presentation.dto.AvaliacaoRequestDto;

import java.util.List;

public interface AvaliacaoService {
    AvaliacaoDto criar(AvaliacaoRequestDto dto);
    List<AvaliacaoDto> listarTodos();
    void deletar(Long id);
}
