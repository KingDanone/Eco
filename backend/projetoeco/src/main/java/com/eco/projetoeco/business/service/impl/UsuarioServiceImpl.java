package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.exception.ResourceNotFoundException;
import com.eco.projetoeco.business.mapper.UsuarioMapper;
import com.eco.projetoeco.data.model.enuns.UserRole;
import com.eco.projetoeco.presentation.dto.UsuarioDTO;
import com.eco.projetoeco.presentation.dto.UsuarioSenhaDTO;
import com.eco.projetoeco.data.model.Usuario;
import com.eco.projetoeco.data.repository.UsuarioRepository;
import com.eco.projetoeco.business.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository repository, UsuarioMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Procurando usuário por email ou CPF: {}", username);
        return repository.findByEmailOrCpf(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email ou CPF: " + username));
    }

    @Override
    public UsuarioDTO criarAdmin(UsuarioDTO dto) {
        log.info("Criando administrador");
        Usuario usuario = mapper.toEntity(dto);
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setRole(UserRole.ADMIN);
        Usuario salvo = repository.save(usuario);
        log.info("Usuario administrador criado");
        return mapper.toDTO(salvo);
    }

    @Override
    public UsuarioDTO criar(UsuarioDTO dto) {
        log.info("Criando usuario comum");
        Usuario usuario = mapper.toEntity(dto);
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setRole(UserRole.USER);
        Usuario salvo = repository.save(usuario);
        log.info("Usuario comum criado");
        return mapper.toDTO(salvo);
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        return mapper.toDTO(repository.findAll());
    }

    @Override
    public UsuarioDTO editar(String cpf, UsuarioDTO dto) {
        Usuario usuario = repository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o CPF: " + cpf));

        usuario.setNome(dto.getNome());
        usuario.setNickname(dto.getNickname());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());

        Usuario atualizado = repository.save(usuario);
        return mapper.toDTO(atualizado);
    }

    @Override
    public void alterarSenha(String cpf, UsuarioSenhaDTO dto) {
        Usuario usuario = repository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o CPF: " + cpf));

        if (!passwordEncoder.matches(dto.getCurrentPassword(), usuario.getSenha())) {
            throw new RuntimeException("Senha atual incorreta");
        }

        usuario.setSenha(passwordEncoder.encode(dto.getNewPassword()));
        repository.save(usuario);
    }

    @Override
    public UsuarioDTO buscarPorCpf(String cpf) {
        Usuario usuario = repository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o CPF: " + cpf));
        return mapper.toDTO(usuario);
    }
}
