package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.presentation.dto.UsuarioDTO;
import com.eco.projetoeco.presentation.dto.UsuarioSenhaDTO;
import com.eco.projetoeco.data.model.Usuario;
import com.eco.projetoeco.data.model.UsuarioComum;
import com.eco.projetoeco.data.repository.UsuarioRepository;
import com.eco.projetoeco.business.service.UsuarioService;
import org.springframework.stereotype.Service;
import static com.eco.projetoeco.business.mapper.ObjectMapper.parseObject;
import static com.eco.projetoeco.business.mapper.ObjectMapper.parseListObjects;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    public UsuarioServiceImpl(UsuarioRepository repository) {this.repository = repository;}


    @Override
    public UsuarioDTO criar(UsuarioDTO dto) {
        UsuarioComum usuario = parseObject(dto, UsuarioComum.class);
        Usuario salvo = repository.save(usuario);
        return parseObject(salvo, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO autenticar(String identifier, String senha) {
        Usuario usuario = repository.findByCpf(identifier)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }
        return parseObject(usuario, UsuarioDTO.class);
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        return parseListObjects(repository.findAll(), UsuarioDTO.class);
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
        return parseObject(atualizado, UsuarioDTO.class);
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
        return parseObject(usuario, UsuarioDTO.class);
    }
}
