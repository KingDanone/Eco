package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.service.AtendimentoService;
import com.eco.projetoeco.data.model.Atendimento;
import com.eco.projetoeco.data.model.Denuncia;
import com.eco.projetoeco.data.repository.AtendimentoRepository;
import com.eco.projetoeco.data.repository.DenunciaRepository;
import com.eco.projetoeco.data.repository.UsuarioRepository;
import com.eco.projetoeco.presentation.dto.AtendimentoDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.eco.projetoeco.business.mapper.ObjectMapper.parseListObjects;
import static com.eco.projetoeco.business.mapper.ObjectMapper.parseObject;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DenunciaRepository denunciaRepository;

    public AtendimentoServiceImpl(
            AtendimentoRepository atendimentoRepository,
            UsuarioRepository usuarioRepository,
            DenunciaRepository denunciaRepository) {
        this.atendimentoRepository = atendimentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.denunciaRepository = denunciaRepository;
    }

    @Override
    @Transactional
    public AtendimentoDTO criar(AtendimentoDTO requestDto) {
        Denuncia denuncia = denunciaRepository.findById(requestDto.getDenuncia().getId())
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada"));

        Atendimento atendimento = new Atendimento();
        atendimento.setDataAtendimento(requestDto.getDataAtendimento());
        atendimento.setDenuncia(denuncia);

        Atendimento salvo = atendimentoRepository.save(atendimento);

        return parseObject(salvo, AtendimentoDTO.class);
    }

    @Override
    public List<AtendimentoDTO> listarTodos() {
        return parseListObjects(atendimentoRepository.findAll(), AtendimentoDTO.class);
    }

    @Override
    public Optional<AtendimentoDTO> buscarPorId(Long protocolo) {
        return atendimentoRepository.findById(protocolo)
                .map(atendimento -> parseObject(atendimento, AtendimentoDTO.class));
    }

    @Override
    public void deletar(Long protocolo) {
        atendimentoRepository.deleteById(protocolo);
    }
}
