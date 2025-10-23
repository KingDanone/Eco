package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.mapper.AtendimentoMapper;
import com.eco.projetoeco.business.mapper.DenunciaMapper;
import com.eco.projetoeco.business.service.AtendimentoService;
import com.eco.projetoeco.data.model.Denuncia;
import com.eco.projetoeco.data.model.enuns.StatusDenuncia;
import com.eco.projetoeco.data.repository.AtendimentoRepository;
import com.eco.projetoeco.data.repository.DenunciaRepository;
import com.eco.projetoeco.presentation.dto.denunciadto.DenunciaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final DenunciaRepository denunciaRepository;
    private final AtendimentoMapper mapper;
    private final DenunciaMapper denunciaMapper;

    public AtendimentoServiceImpl(
            AtendimentoRepository atendimentoRepository,
            DenunciaRepository denunciaRepository,
            AtendimentoMapper mapper,
            DenunciaMapper denunciaMapper) {
        this.atendimentoRepository = atendimentoRepository;
        this.denunciaRepository = denunciaRepository;
        this.mapper = mapper;
        this.denunciaMapper = denunciaMapper;
    }

    @Override
    public void deletar(Long protocolo) {
        atendimentoRepository.deleteById(protocolo);
    }

    @Override
    public List<DenunciaDTO> buscarDenuncias(StatusDenuncia status) {
        List<Denuncia> denuncias;
        if (status == null) {
            denuncias = denunciaRepository.findAllWithAssociations();
        } else {
            denuncias = denunciaRepository.findAllByStatusWithAssociations(status);
        }
        return denunciaMapper.toDTO(denuncias);
    }
}
