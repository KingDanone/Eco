package com.eco.projetoeco.business.service.impl;

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

    public RespostaServiceImpl(RespostaRepository respostaRepository, AtendimentoRepository atendimentoRepository) {
        this.respostaRepository = respostaRepository;
        this.atendimentoRepository = atendimentoRepository;
    }

    public RespostaDTO criarResposta(RespostaDTO dto) {
        Atendimento atendimento = atendimentoRepository.findById(dto.getAtendimentoId())
                .orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));

        Resposta resposta = new Resposta();
        resposta.setMensagem(dto.getMensagem());
        resposta.setAtendimento(atendimento);

        Resposta salva = respostaRepository.save(resposta);
        atendimento.adicionarResposta(salva);
        atendimentoRepository.save(atendimento);

        return RespostaDTO.builder()
                .id(salva.getId())
                .mensagem(salva.getMensagem())
                .dataResposta(salva.getDataResposta())
                .atendimentoId(atendimento.getProtocolo())
                .build();
    }
}
