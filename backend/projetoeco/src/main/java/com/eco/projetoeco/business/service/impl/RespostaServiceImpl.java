package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.exception.ResourceNotFoundException;
import com.eco.projetoeco.business.mapper.RespostaMapper;
import com.eco.projetoeco.business.service.RespostaService;
import com.eco.projetoeco.domain.model.Denuncia;
import com.eco.projetoeco.domain.model.Resposta;
import com.eco.projetoeco.domain.model.enuns.StatusDenuncia;
import com.eco.projetoeco.domain.repository.DenunciaRepository;
import com.eco.projetoeco.domain.repository.RespostaRepository;
import com.eco.projetoeco.presentation.dto.RespostaDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RespostaServiceImpl implements RespostaService {

    private final RespostaRepository respostaRepository;
    private final DenunciaRepository denunciaRepository;
    private final RespostaMapper mapper;

    public RespostaServiceImpl(RespostaRepository respostaRepository,
                               DenunciaRepository denunciaRepository,
                               RespostaMapper mapper) {
        this.respostaRepository = respostaRepository;
        this.denunciaRepository = denunciaRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public RespostaDTO criarResposta(RespostaDTO dto) {
        // 1. Buscar a Denuncia
        Denuncia denuncia = denunciaRepository.findById(dto.getDenunciaId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Denuncia com Id" + dto.getDenunciaId() + " não encontrada")
                );

        // 2. Mapear DTO para entidade Resposta
        Resposta resposta = mapper.toEntity(dto);

        // 3. Associar a Resposta à Denuncia
        resposta.setDenuncia(denuncia);

        // 4. Mudar o status da Denuncia
        denuncia.setStatus(StatusDenuncia.EM_ANALISE);

        // 5. Salvar a Resposta (e a Denuncia será atualizada pela transação)
        Resposta respostaSalva = respostaRepository.save(resposta);

        // 6. Retornar o DTO da resposta salva
        return mapper.toDTO(respostaSalva);
    }
}
