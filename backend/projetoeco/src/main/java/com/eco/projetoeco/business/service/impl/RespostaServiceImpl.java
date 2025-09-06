package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.mapper.RespostaMapper;
import com.eco.projetoeco.business.service.RespostaService;
import com.eco.projetoeco.data.model.Atendimento;
import com.eco.projetoeco.data.model.Resposta;
import com.eco.projetoeco.data.repository.AtendimentoRepository;
import com.eco.projetoeco.data.repository.RespostaRepository;
import com.eco.projetoeco.presentation.dto.RespostaDTO;
import org.springframework.stereotype.Service;

@Service
public class RespostaServiceImpl implements RespostaService {

    private final RespostaRepository respostaRepository;
    private final AtendimentoRepository atendimentoRepository;
    private final RespostaMapper mapper;

    public RespostaServiceImpl(RespostaRepository respostaRepository,
                               AtendimentoRepository atendimentoRepository,
                               RespostaMapper mapper) {
        this.respostaRepository = respostaRepository;
        this.atendimentoRepository = atendimentoRepository;
        this.mapper = mapper;
    }

    public RespostaDTO criarResposta(RespostaDTO dto) {
        Atendimento atendimento = atendimentoRepository.findById(dto.getAtendimentoId())
                .orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));

        Resposta resposta = mapper.toEntity(dto);
        resposta.setAtendimento(atendimento);

        Resposta salva = respostaRepository.save(resposta);
        atendimento.adicionarResposta(salva);
        atendimentoRepository.save(atendimento);

        return mapper.toDTO(salva);
    }
}
