package com.eco.projetoeco.service;

import com.eco.projetoeco.dto.AvaliacaoDto;
import com.eco.projetoeco.dto.AvaliacaoRequestDto;

import java.util.List;

public interface AvaliacaoService {
    AvaliacaoDto criar(AvaliacaoRequestDto dto);
    List<AvaliacaoDto> listarTodos();
    void deletar(Long id);
}
