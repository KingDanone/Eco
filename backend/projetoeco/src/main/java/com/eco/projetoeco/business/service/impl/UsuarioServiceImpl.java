package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.mapper.UsuarioMapper;
import com.eco.projetoeco.presentation.dto.UsuarioDTO;
import com.eco.projetoeco.presentation.dto.UsuarioSenhaDTO;
import com.eco.projetoeco.data.model.Usuario;
import com.eco.projetoeco.data.repository.UsuarioRepository;
import com.eco.projetoeco.business.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioServiceImpl(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public UsuarioDTO criar(UsuarioDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        Usuario salvo = repository.save(usuario);
        return mapper.toDTO(salvo);
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        return mapper.toDTO(repository.findAll());
    }

    @Override
    public UsuarioDTO editar(String cpf, UsuarioDTO dto) {
        Usuario usuario = repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dto.getNome());
        usuario.setNickname(dto.getNickname());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());

        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            usuario.setSenha(dto.getSenha());
        }

        Usuario atualizado = repository.save(usuario);
        return mapper.toDTO(atualizado);
    }

    @Override
    public void alterarSenha(String cpf, UsuarioSenhaDTO dto) {
        Usuario usuario = repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!usuario.getSenha().equals(dto.getCurrentPassword())) {
            throw new RuntimeException("Senha atual incorreta");
        }

        usuario.setSenha(dto.getNewPassword());
        repository.save(usuario);
    }

    @Override
    public UsuarioDTO buscarPorCpf(String cpf) {
        Usuario usuario = repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return mapper.toDTO(usuario);
    }
}
