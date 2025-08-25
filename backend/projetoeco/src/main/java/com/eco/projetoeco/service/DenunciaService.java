package com.eco.projetoeco.service;

import com.eco.projetoeco.dto.DenunciaDto;
import com.eco.projetoeco.dto.DenunciaRequestDto;

import java.util.List;
import java.util.Optional;

public interface DenunciaService {
    DenunciaDto criarDenuncia(DenunciaRequestDto requestDto);
    List<DenunciaDto> listarTodas();
    Optional<DenunciaDto> buscarPorId(Long id);
}
