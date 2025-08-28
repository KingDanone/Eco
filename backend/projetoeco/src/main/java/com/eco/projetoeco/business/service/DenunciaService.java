package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.DenunciaDTO;

import java.util.List;
import java.util.Optional;

public interface DenunciaService {
    DenunciaDTO criarDenuncia(DenunciaDTO dto);
    List<DenunciaDTO> listarTodas();
    Optional<DenunciaDTO> buscarPorId(Long id);
}
