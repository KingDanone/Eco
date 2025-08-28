package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.AtendimentoDTO;

import java.util.List;
import java.util.Optional;

public interface AtendimentoService {
    AtendimentoDTO criar(AtendimentoDTO dto);
    List<AtendimentoDTO> listarTodos();
    Optional<AtendimentoDTO> buscarPorId(Long protocolo);
    void deletar(Long protocolo);
}
