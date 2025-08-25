package com.eco.projetoeco.service;

import com.eco.projetoeco.dto.AtendimentoDto;
import com.eco.projetoeco.dto.AtendimentoRequestDto;

import java.util.List;
import java.util.Optional;

public interface AtendimentoService {
    AtendimentoDto criar(AtendimentoRequestDto dto);
    List<AtendimentoDto> listarTodos();
    Optional<AtendimentoDto> buscarPorId(Long protocolo);
    void deletar(Long protocolo);
}
