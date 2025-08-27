package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.HorariosColetaDto;
import com.eco.projetoeco.presentation.dto.HorariosColetaRequestDto;

import java.util.List;
import java.util.Optional;

public interface HorariosColetaService {
    HorariosColetaDto criar(HorariosColetaRequestDto request);
    List<HorariosColetaDto> listarTodos();
    Optional<HorariosColetaDto> buscarPorId(Long id);
    void deletar(Long id);
}
