package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.JogosDto;
import com.eco.projetoeco.presentation.dto.JogosRequestDto;

import java.util.List;

public interface JogosService {
    JogosDto criar(JogosRequestDto dto);
    List<JogosDto> listarTodos();
    JogosDto buscarPorId(Long id);
    void deletar(Long id);
}
