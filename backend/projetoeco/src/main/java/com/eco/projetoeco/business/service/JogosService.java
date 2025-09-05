package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.JogosDTO;

import java.util.List;

public interface JogosService {
    JogosDTO criar(JogosDTO dto);
    List<JogosDTO> listarTodos();
    JogosDTO buscarPorId(Long id);
    void deletar(Long id);
}
