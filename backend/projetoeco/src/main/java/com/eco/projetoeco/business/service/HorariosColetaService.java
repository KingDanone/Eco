package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.HorariosColetaDTO;

import java.util.List;
import java.util.Optional;

public interface HorariosColetaService {
    HorariosColetaDTO criar(HorariosColetaDTO dto);
    List<HorariosColetaDTO> listarTodos();
    Optional<HorariosColetaDTO> buscarPorId(Long id);
    void deletar(Long id);
}
