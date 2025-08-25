package com.eco.projetoeco.service.impl;

import com.eco.projetoeco.dto.DenunciaDto;
import com.eco.projetoeco.dto.DenunciaRequestDto;
import com.eco.projetoeco.dto.EnderecoResumoDto;
import com.eco.projetoeco.dto.UsuarioResumoDto;
import com.eco.projetoeco.model.Denuncia;
import com.eco.projetoeco.model.Endereco;
import com.eco.projetoeco.model.Usuario;
import com.eco.projetoeco.repository.DenunciaRepository;
import com.eco.projetoeco.repository.EnderecoRepository;
import com.eco.projetoeco.repository.UsuarioRepository;
import com.eco.projetoeco.service.DenunciaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DenunciaServiceImpl implements DenunciaService {

    private final DenunciaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;

    public DenunciaServiceImpl(DenunciaRepository repository,
                               UsuarioRepository usuarioRepository,
                               EnderecoRepository enderecoRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    @Transactional
    public DenunciaDto criarDenuncia(DenunciaRequestDto request) {

        // Buscar usuário e endereço
        Usuario usuario = usuarioRepository.findById(request.getUsuarioCpf())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Endereco endereco = enderecoRepository.findById(request.getEnderecoCep())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        // Criar denúncia
        Denuncia denuncia = new Denuncia();
        denuncia.setTitulo(request.getTitulo());
        denuncia.setDescricao(request.getDescricao());
        denuncia.setUsuario(usuario);
        denuncia.setEndereco(endereco);

        Denuncia salva = repository.save(denuncia);

        // Converter para DTO
        UsuarioResumoDto usuarioDto = new UsuarioResumoDto(
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getNickname(),
                usuario.getEmail(),
                usuario.getTelefone()
        );

        EnderecoResumoDto enderecoDto = new EnderecoResumoDto(
                endereco.getCep(),
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getLogradouro()
        );

        DenunciaDto dto = new DenunciaDto(
                salva.getId(),
                salva.getTitulo(),
                salva.getDescricao(),
                salva.getDataCriacao(),
                salva.getDataAtualizacao(),
                usuarioDto,
                enderecoDto
        );


        return new DenunciaDto(
                salva.getId(),
                salva.getTitulo(),
                salva.getDescricao(),
                salva.getDataCriacao(),
                salva.getDataAtualizacao(),
                usuarioDto,
                enderecoDto // ← ✅ Aqui usamos o DTO simplificado
        );
    }

    @Override
    public List<DenunciaDto> listarTodas() {
        return repository.findAll().stream()
                .map(d -> {
                    Usuario u = d.getUsuario();
                    UsuarioResumoDto usuarioDto = new UsuarioResumoDto(
                            u.getCpf(), u.getNome(), u.getNickname(), u.getEmail(), u.getTelefone()
                    );

                    Endereco e = d.getEndereco();
                    EnderecoResumoDto enderecoDto = new EnderecoResumoDto(
                            e.getCep(), e.getEstado(), e.getCidade(), e.getBairro(), e.getLogradouro()
                    );

                    return new DenunciaDto(
                            d.getId(), d.getTitulo(), d.getDescricao(),
                            d.getDataCriacao(), d.getDataAtualizacao(),
                            usuarioDto,
                            enderecoDto
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DenunciaDto> buscarPorId(Long id) {
        return repository.findById(id)
                .map(d -> {
                    Usuario u = d.getUsuario();
                    UsuarioResumoDto usuarioDto = new UsuarioResumoDto(
                            u.getCpf(), u.getNome(), u.getNickname(), u.getEmail(), u.getTelefone()
                    );

                    Endereco e = d.getEndereco();
                    EnderecoResumoDto enderecoDto = new EnderecoResumoDto(
                            e.getCep(), e.getEstado(), e.getCidade(), e.getBairro(), e.getLogradouro()
                    );

                    return new DenunciaDto(
                            d.getId(), d.getTitulo(), d.getDescricao(),
                            d.getDataCriacao(), d.getDataAtualizacao(),
                            usuarioDto,
                            enderecoDto
                    );
                });
    }
}
