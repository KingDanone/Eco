package com.eco.projetoeco.business.service;

import com.eco.projetoeco.domain.model.enuns.StatusDenuncia;
import com.eco.projetoeco.presentation.dto.denunciadto.DenunciaDTO;

import java.util.List;

public interface AtendimentoService {
    void deletar(Long protocolo);

    List<DenunciaDTO> buscarDenuncias(StatusDenuncia status);
}
