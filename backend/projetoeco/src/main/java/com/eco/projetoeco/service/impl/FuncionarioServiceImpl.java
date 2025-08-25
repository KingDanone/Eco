package com.eco.projetoeco.service.impl;

import com.eco.projetoeco.dto.FuncionarioDto;
import com.eco.projetoeco.dto.FuncionarioRequestDto;
import com.eco.projetoeco.model.Funcionario;
import com.eco.projetoeco.repository.FuncionarioRepository;
import com.eco.projetoeco.service.FuncionarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioServiceImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public FuncionarioDto criar(FuncionarioRequestDto request) {
        Funcionario funcionario = new Funcionario();
        funcionario.setCodigo(request.getCodigo());
        funcionario.setNome(request.getNome());
        funcionario.setEmail(request.getEmail());
        funcionario.setTelefone(request.getTelefone());
        funcionario.setSenha(request.getSenha());

        Funcionario salvo = repository.save(funcionario);

        return new FuncionarioDto(
                salvo.getCodigo(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getTelefone()
        );
    }

    @Override
    public List<FuncionarioDto> listarTodos() {
        return repository.findAll().stream()
                .map(f -> new FuncionarioDto(
                        f.getCodigo(), f.getNome(), f.getEmail(), f.getTelefone()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FuncionarioDto> buscarPorCodigo(String codigo) {
        return repository.findById(codigo)
                .map(f -> new FuncionarioDto(
                        f.getCodigo(), f.getNome(), f.getEmail(), f.getTelefone()
                ));
    }
}
