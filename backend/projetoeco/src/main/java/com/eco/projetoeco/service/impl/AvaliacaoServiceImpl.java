package com.eco.projetoeco.service.impl;

import com.eco.projetoeco.dto.AvaliacaoDto;
import com.eco.projetoeco.dto.AvaliacaoRequestDto;
import com.eco.projetoeco.model.Avaliacao;
import com.eco.projetoeco.model.Jogos;
import com.eco.projetoeco.model.Usuario;
import com.eco.projetoeco.repository.AvaliacaoRepository;
import com.eco.projetoeco.repository.JogosRepository;
import com.eco.projetoeco.repository.UsuarioRepository;
import com.eco.projetoeco.service.AvaliacaoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final JogosRepository jogosRepository;

    public AvaliacaoServiceImpl(AvaliacaoRepository avaliacaoRepository,
                                UsuarioRepository usuarioRepository,
                                JogosRepository jogosRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.jogosRepository = jogosRepository;
    }

    @Override
    public AvaliacaoDto criar(AvaliacaoRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getCpfUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Jogos jogo = jogosRepository.findById(dto.getIdJogo())
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setUsuario(usuario);
        avaliacao.setJogo(jogo);
        avaliacao.setNivelAvaliacao(dto.getNivelAvaliacao());
        avaliacao.setComentario(dto.getComentario());

        Avaliacao salva = avaliacaoRepository.save(avaliacao);

        return new AvaliacaoDto(
                salva.getId(),
                usuario.getCpf(),
                jogo.getId(),
                salva.getNivelAvaliacao(),
                salva.getComentario(),
                salva.getDataCriacao()
        );
    }

    @Override
    public List<AvaliacaoDto> listarTodos() {
        return avaliacaoRepository.findAll().stream()
                .map(a -> new AvaliacaoDto(
                        a.getId(),
                        a.getUsuario().getCpf(),
                        a.getJogo().getId(),
                        a.getNivelAvaliacao(),
                        a.getComentario(),
                        a.getDataCriacao()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void deletar(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}
