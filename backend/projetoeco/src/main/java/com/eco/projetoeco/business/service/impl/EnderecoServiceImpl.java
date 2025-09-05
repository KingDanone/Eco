package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.presentation.dto.EnderecoDTO;
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
    public EnderecoDTO criarEndereco(EnderecoDTO dto) {
        Endereco endereco = new Endereco(
                null, // id será gerado automaticamente
                dto.getCep(),
                dto.getEstado(),
                dto.getCidade(),
                dto.getBairro(),
                dto.getLogradouro(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        Endereco salvo = repository.save(endereco);
        return new EnderecoDTO(
                salvo.getId(),
                salvo.getCep(), salvo.getEstado(), salvo.getCidade(),
                salvo.getBairro(), salvo.getLogradouro()
        );
    }

    @Override
    public List<EnderecoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(e -> new EnderecoDTO(
                        e.getId(),
                        e.getCep(), e.getEstado(), e.getCidade(),
                        e.getBairro(), e.getLogradouro()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletarPorCep(String cep) {
        if (!repository.existsByCep(cep)) {
            throw new RuntimeException("Endereço não encontrado para o CEP: " + cep);
        }
        repository.deleteByCep(cep);
    }
}
