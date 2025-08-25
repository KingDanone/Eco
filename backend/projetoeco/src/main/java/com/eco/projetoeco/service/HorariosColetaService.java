package com.eco.projetoeco.service;

import com.eco.projetoeco.dto.HorariosColetaDto;
import com.eco.projetoeco.dto.HorariosColetaRequestDto;

import java.util.List;
import java.util.Optional;

public interface HorariosColetaService {
    HorariosColetaDto criar(HorariosColetaRequestDto request);
    List<HorariosColetaDto> listarTodos();
    Optional<HorariosColetaDto> buscarPorId(Long id);
    void deletar(Long id);
}
