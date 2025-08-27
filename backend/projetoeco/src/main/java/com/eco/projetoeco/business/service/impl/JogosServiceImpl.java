package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.presentation.dto.JogosDto;
import com.eco.projetoeco.presentation.dto.JogosRequestDto;
import com.eco.projetoeco.data.model.Jogos;
import com.eco.projetoeco.data.repository.JogosRepository;
import com.eco.projetoeco.business.service.JogosService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JogosServiceImpl implements JogosService {

    private final JogosRepository repository;

    public JogosServiceImpl(JogosRepository repository) {
        this.repository = repository;
    }

    @Override
    public JogosDto criar(JogosRequestDto dto) {
        Jogos jogo = new Jogos();
        jogo.setNome(dto.getNome());
        jogo.setGenero(dto.getGenero());
        jogo.setDescricao(dto.getDescricao());
        jogo.setDataLancamento(dto.getDataLancamento());
        jogo.setDesenvolvedor(dto.getDesenvolvedor());
        jogo.setLinkJogo(dto.getLinkJogo());

        Jogos salvo = repository.save(jogo);

        return new JogosDto(
                salvo.getId(), salvo.getNome(), salvo.getGenero(),
                salvo.getDescricao(), salvo.getDataLancamento(),
                salvo.getDesenvolvedor(), salvo.getLinkJogo()
        );
    }

    @Override
    public List<JogosDto> listarTodos() {
        return repository.findAll().stream()
                .map(j -> new JogosDto(
                        j.getId(), j.getNome(), j.getGenero(), j.getDescricao(),
                        j.getDataLancamento(), j.getDesenvolvedor(), j.getLinkJogo()))
                .collect(Collectors.toList());
    }

    @Override
    public JogosDto buscarPorId(Long id) {
        Jogos jogo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));
        return new JogosDto(
                jogo.getId(), jogo.getNome(), jogo.getGenero(), jogo.getDescricao(),
                jogo.getDataLancamento(), jogo.getDesenvolvedor(), jogo.getLinkJogo()
        );
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
