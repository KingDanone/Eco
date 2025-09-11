package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.exception.ResourceNotFoundException;
import com.eco.projetoeco.business.mapper.EnderecoMapper;
import com.eco.projetoeco.presentation.dto.EnderecoDTO;
import com.eco.projetoeco.data.model.Endereco;
import com.eco.projetoeco.data.repository.EnderecoRepository;
import com.eco.projetoeco.business.service.EnderecoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository repository;
    private final EnderecoMapper mapper;

    public EnderecoServiceImpl(EnderecoRepository repository, EnderecoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public EnderecoDTO criarEndereco(EnderecoDTO dto) {
        Endereco endereco = mapper.toEntity(dto);
        Endereco salvo = repository.save(endereco);
        return mapper.toDTO(salvo);
    }

    @Override
    public List<EnderecoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletarPorCep(String cep) {
        if (!repository.existsByCep(cep)) {
            throw new ResourceNotFoundException("Endereço não encontrado para o CEP: " + cep);
        }
        repository.deleteByCep(cep);
    }
}
