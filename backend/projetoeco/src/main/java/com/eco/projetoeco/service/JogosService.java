package com.eco.projetoeco.service;

import com.eco.projetoeco.dto.JogosDto;
import com.eco.projetoeco.dto.JogosRequestDto;

import java.util.List;

public interface JogosService {
    JogosDto criar(JogosRequestDto dto);
    List<JogosDto> listarTodos();
    JogosDto buscarPorId(Long id);
    void deletar(Long id);
}
