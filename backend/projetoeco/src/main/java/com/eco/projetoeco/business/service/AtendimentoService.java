package com.eco.projetoeco.business.service;

import com.eco.projetoeco.data.model.enuns.StatusDenuncia;
import com.eco.projetoeco.presentation.dto.AtendimentoDTO;
import com.eco.projetoeco.presentation.dto.DenunciaDTO;

import java.util.List;
import java.util.Optional;

public interface AtendimentoService {
    void deletar(Long protocolo);

    List<DenunciaDTO> buscarDenuncias(StatusDenuncia status);
}
