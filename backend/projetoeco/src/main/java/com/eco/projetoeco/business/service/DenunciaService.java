package com.eco.projetoeco.business.service;

import com.eco.projetoeco.presentation.dto.denunciadto.DenunciaDTO;
import com.eco.projetoeco.presentation.dto.denunciadto.EditarDenunciaDTO;
import com.eco.projetoeco.presentation.dto.denunciadto.UpdateDenunciaStatusDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DenunciaService {
    DenunciaDTO criarDenuncia(DenunciaDTO dto, MultipartFile anexo, UserDetails userDetails);
    List<DenunciaDTO> listarTodas();
    List<DenunciaDTO> listarPorUsuario(UserDetails userDetails);
    Optional<DenunciaDTO> buscarPorId(Long id);
    DenunciaDTO editarDenuncia(Long id, EditarDenunciaDTO dados, UserDetails userDetails);
    DenunciaDTO atualizarStatus(Long id, UpdateDenunciaStatusDTO statusDTO);
    boolean isOwner(Long id, String username);
}
