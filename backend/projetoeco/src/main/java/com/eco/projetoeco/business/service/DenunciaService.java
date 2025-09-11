package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.denunciadto.DenunciaDTO;
import com.eco.projetoeco.presentation.dto.denunciadto.UpdateDenunciaStatusDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface DenunciaService {
    DenunciaDTO criarDenuncia(DenunciaDTO dto, UserDetails userDetails);
    List<DenunciaDTO> listarTodas();
    Optional<DenunciaDTO> buscarPorId(Long id);
    DenunciaDTO atualizarStatus(Long id, UpdateDenunciaStatusDTO statusDTO);
    boolean isOwner(Long id, String username);
}
