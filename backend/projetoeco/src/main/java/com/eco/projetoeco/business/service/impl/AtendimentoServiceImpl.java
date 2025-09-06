package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.mapper.AtendimentoMapper;
import com.eco.projetoeco.business.service.AtendimentoService;
import com.eco.projetoeco.data.model.Atendimento;
import com.eco.projetoeco.data.model.Denuncia;
import com.eco.projetoeco.data.repository.AtendimentoRepository;
import com.eco.projetoeco.data.repository.DenunciaRepository;
import com.eco.projetoeco.presentation.dto.AtendimentoDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final DenunciaRepository denunciaRepository;
    private final AtendimentoMapper mapper;

    public AtendimentoServiceImpl(
            AtendimentoRepository atendimentoRepository,
            DenunciaRepository denunciaRepository,
            AtendimentoMapper mapper) {
        this.atendimentoRepository = atendimentoRepository;
        this.denunciaRepository = denunciaRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public AtendimentoDTO criar(AtendimentoDTO requestDto) {
        Denuncia denuncia = denunciaRepository.findById(requestDto.getDenuncia().getId())
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada"));

        Atendimento atendimento = mapper.toEntity(requestDto);
        atendimento.setDenuncia(denuncia);

        Atendimento salvo = atendimentoRepository.save(atendimento);

        return mapper.toDTO(salvo);
    }

    @Override
    public List<AtendimentoDTO> listarTodos() {
        return mapper.toDTO(atendimentoRepository.findAll());
    }

    @Override
    public Optional<AtendimentoDTO> buscarPorId(Long protocolo) {
        return atendimentoRepository.findById(protocolo)
                .map(mapper::toDTO);
    }

    @Override
    public void deletar(Long protocolo) {
        atendimentoRepository.deleteById(protocolo);
    }
}
