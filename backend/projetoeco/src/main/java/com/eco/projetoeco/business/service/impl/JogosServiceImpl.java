package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.exception.ResourceNotFoundException;
import com.eco.projetoeco.business.mapper.JogosMapper;
import com.eco.projetoeco.presentation.dto.JogosDTO;
import com.eco.projetoeco.domain.model.Jogos;
import com.eco.projetoeco.domain.repository.JogosRepository;
import com.eco.projetoeco.business.service.JogosService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogosServiceImpl implements JogosService {

    private final JogosRepository repository;
    private final JogosMapper mapper;

    public JogosServiceImpl(JogosRepository repository, JogosMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public JogosDTO criar(JogosDTO dto) {
        Jogos jogo = mapper.toEntity(dto);
        Jogos salvo = repository.save(jogo);
        return mapper.toDTO(salvo);
    }

    @Override
    public List<JogosDTO> listarTodos() {
        return mapper.toDTO(repository.findAll());
    }

    @Override
    public JogosDTO buscarPorId(Long id) {
        Jogos jogo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado"));
        return mapper.toDTO(jogo);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
