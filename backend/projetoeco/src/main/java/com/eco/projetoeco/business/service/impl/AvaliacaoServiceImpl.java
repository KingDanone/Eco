package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.exception.ResourceNotFoundException;
import com.eco.projetoeco.business.mapper.AvaliacaoMapper;
import com.eco.projetoeco.presentation.dto.AvaliacaoDTO;
import com.eco.projetoeco.data.model.Avaliacao;
import com.eco.projetoeco.data.model.Jogos;
import com.eco.projetoeco.data.model.Usuario;
import com.eco.projetoeco.data.repository.AvaliacaoRepository;
import com.eco.projetoeco.data.repository.JogosRepository;
import com.eco.projetoeco.data.repository.UsuarioRepository;
import com.eco.projetoeco.business.service.AvaliacaoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final JogosRepository jogosRepository;
    private final AvaliacaoMapper mapper;

    public AvaliacaoServiceImpl(AvaliacaoRepository avaliacaoRepository,
                                UsuarioRepository usuarioRepository,
                                JogosRepository jogosRepository,
                                AvaliacaoMapper mapper) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.jogosRepository = jogosRepository;
        this.mapper = mapper;
    }

    @Override
    public AvaliacaoDTO criar(AvaliacaoDTO dto) {
        Usuario usuario = usuarioRepository.findByCpf(dto.getCpfUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Jogos jogo = jogosRepository.findById(dto.getIdJogo())
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado"));

        Avaliacao avaliacao = mapper.toEntity(dto);
        avaliacao.setUsuario(usuario);
        avaliacao.setJogo(jogo);

        Avaliacao salva = avaliacaoRepository.save(avaliacao);

        return mapper.toDTO(salva);
    }

    @Override
    public List<AvaliacaoDTO> listarTodos() {
        return mapper.toDTO(avaliacaoRepository.findAll());
    }

    @Override
    public void deletar(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}
