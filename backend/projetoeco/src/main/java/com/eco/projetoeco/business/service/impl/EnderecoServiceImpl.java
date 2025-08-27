package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.presentation.dto.EnderecoDto;
import com.eco.projetoeco.presentation.dto.EnderecoRequestDto;
import com.eco.projetoeco.data.model.Endereco;
import com.eco.projetoeco.data.repository.EnderecoRepository;
import com.eco.projetoeco.business.service.EnderecoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository repository;

    public EnderecoServiceImpl(EnderecoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public EnderecoDto criarEndereco(EnderecoRequestDto dto) {
        Endereco endereco = new Endereco(
                dto.getCep(),
                dto.getEstado(),
                dto.getCidade(),
                dto.getBairro(),
                dto.getLogradouro(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        Endereco salvo = repository.save(endereco);
        return new EnderecoDto(
                salvo.getCep(), salvo.getEstado(), salvo.getCidade(),
                salvo.getBairro(), salvo.getLogradouro()
        );
    }

    @Override
    public List<EnderecoDto> listarTodos() {
        return repository.findAll().stream()
                .map(e -> new EnderecoDto(
                        e.getCep(), e.getEstado(), e.getCidade(),
                        e.getBairro(), e.getLogradouro()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletarPorCep(String cep) {
        if (!repository.existsById(cep)) {
            throw new RuntimeException("Endereço não encontrado para o CEP: " + cep);
        }
        repository.deleteById(cep);
    }
}
