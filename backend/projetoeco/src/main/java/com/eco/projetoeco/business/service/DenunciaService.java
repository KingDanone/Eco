package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.DenunciaDto;
import com.eco.projetoeco.presentation.dto.DenunciaRequestDto;

import java.util.List;
import java.util.Optional;

public interface DenunciaService {
    DenunciaDto criarDenuncia(DenunciaRequestDto requestDto);
    List<DenunciaDto> listarTodas();
    Optional<DenunciaDto> buscarPorId(Long id);
}
