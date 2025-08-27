package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.AtendimentoDto;
import com.eco.projetoeco.presentation.dto.AtendimentoRequestDto;

import java.util.List;
import java.util.Optional;

public interface AtendimentoService {
    AtendimentoDto criar(AtendimentoRequestDto dto);
    List<AtendimentoDto> listarTodos();
    Optional<AtendimentoDto> buscarPorId(Long protocolo);
    void deletar(Long protocolo);
}
