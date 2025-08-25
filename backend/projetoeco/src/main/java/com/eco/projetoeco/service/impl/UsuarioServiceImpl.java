package com.eco.projetoeco.service.impl;

import com.eco.projetoeco.dto.UsuarioDto;
import com.eco.projetoeco.dto.UsuarioRequestDto;
import com.eco.projetoeco.dto.UsuarioSenhaUpdateDto;
import com.eco.projetoeco.dto.UsuarioUpdateRequestDto;
import com.eco.projetoeco.model.Usuario;
import com.eco.projetoeco.repository.UsuarioRepository;
import com.eco.projetoeco.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    public UsuarioServiceImpl(UsuarioRepository repository) {this.repository = repository;}


    @Override
    public UsuarioDto criar(UsuarioRequestDto dto) {
        Usuario usuario = new Usuario(
                dto.getCpf(), dto.getNome(), dto.getNickname(),
                dto.getEmail(), dto.getTelefone(), dto.getSenha(),
                null, null);
        Usuario salvo = repository.save(usuario);
        return new UsuarioDto(
                salvo.getCpf(), salvo.getNome(), salvo.getNickname(),
                salvo.getEmail(), salvo.getTelefone()
        );
    }

    @Override
    public UsuarioDto autenticar(String identifier, String senha) {
        Usuario usuario = repository.findByCpf(identifier);
        if (usuario == null) {
            usuario = repository.findByEmail(identifier);
        }
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }
        return new UsuarioDto(
                usuario.getCpf(), usuario.getNome(), usuario.getNickname(),
                usuario.getEmail(), usuario.getTelefone()
        );
    }

    @Override
    public List<UsuarioDto> listarTodos() {
        return repository.findAll().stream()
                .map(u -> new UsuarioDto(
                        u.getCpf(), u.getNome(), u.getNickname(),
                        u.getEmail(), u.getTelefone()
                        ))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDto editar(String cpf, UsuarioUpdateRequestDto dto) {
        Usuario usuario = repository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dto.getNome());
        usuario.setNickname(dto.getNickname());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());

        // Não altera a senha, porque não está no DTO de atualização

        Usuario atualizado = repository.save(usuario);

        return new UsuarioDto(
                atualizado.getCpf(),
                atualizado.getNome(),
                atualizado.getNickname(),
                atualizado.getEmail(),
                atualizado.getTelefone()
        );
    }

    @Override
    public void alterarSenha(String cpf, UsuarioSenhaUpdateDto dto) {
        Usuario usuario = repository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.getSenha().equals(dto.getCurrentPassword())) {
            throw new RuntimeException("Senha atual incorreta");
        }

        usuario.setSenha(dto.getNewPassword());
        repository.save(usuario);
    }

    @Override
    public UsuarioDto buscarPorCpf(String cpf) {
        Usuario usuario = repository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UsuarioDto(
                usuario.getCpf(), usuario.getNome(), usuario.getNickname(),
                usuario.getEmail(), usuario.getTelefone()
                );
    }
}
