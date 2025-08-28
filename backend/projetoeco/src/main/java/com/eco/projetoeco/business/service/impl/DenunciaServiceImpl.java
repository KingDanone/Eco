package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.presentation.dto.DenunciaDTO;
import com.eco.projetoeco.presentation.dto.EnderecoDTO;
import com.eco.projetoeco.presentation.dto.UsuarioDTO;
import com.eco.projetoeco.data.model.Denuncia;
import com.eco.projetoeco.data.model.Endereco;
import com.eco.projetoeco.data.model.Usuario;
import com.eco.projetoeco.data.repository.DenunciaRepository;
import com.eco.projetoeco.data.repository.EnderecoRepository;
import com.eco.projetoeco.data.repository.UsuarioRepository;
import com.eco.projetoeco.business.service.DenunciaService;
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
    public DenunciaDTO criarDenuncia(DenunciaDTO request) {
        // Buscar usuário e endereço
        Usuario usuario = usuarioRepository.findByCpf(request.getUsuario().getCpf())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Endereco endereco = enderecoRepository.findById(request.getEndereco().getCep())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        Denuncia denuncia = new Denuncia();
        denuncia.setTitulo(request.getTitulo());
        denuncia.setDescricao(request.getDescricao());
        denuncia.setUsuario(usuario);
        denuncia.setEndereco(endereco);

        Denuncia salva = repository.save(denuncia);

        // Converter para DTO
        UsuarioDTO usuarioDto = com.eco.projetoeco.business.mapper.ObjectMapper.parseObject(usuario, UsuarioDTO.class);

        EnderecoDTO enderecoDto = new EnderecoDTO(
                endereco.getCep(),
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getLogradouro()
        );

        return new DenunciaDTO(
                salva.getId(),
                salva.getTitulo(),
                salva.getDescricao(),
                salva.getDataCriacao(),
                salva.getDataAtualizacao(),
                usuarioDto,
                enderecoDto
        );
    }

    @Override
    public List<DenunciaDTO> listarTodas() {
        return repository.findAll().stream()
                .map(d -> {
                    Usuario u = d.getUsuario();
                    UsuarioDTO usuarioDto = com.eco.projetoeco.business.mapper.ObjectMapper.parseObject(u, UsuarioDTO.class);

                    Endereco e = d.getEndereco();
                    EnderecoDTO enderecoDto = new EnderecoDTO(
                            e.getCep(), e.getEstado(), e.getCidade(), e.getBairro(), e.getLogradouro()
                    );

                    return new DenunciaDTO(
                            d.getId(), d.getTitulo(), d.getDescricao(),
                            d.getDataCriacao(), d.getDataAtualizacao(),
                            usuarioDto,
                            enderecoDto
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DenunciaDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(d -> {
                    Usuario u = d.getUsuario();
                    UsuarioDTO usuarioDto = com.eco.projetoeco.business.mapper.ObjectMapper.parseObject(u, UsuarioDTO.class);

                    Endereco e = d.getEndereco();
                    EnderecoDTO enderecoDto = new EnderecoDTO(
                            e.getCep(), e.getEstado(), e.getCidade(), e.getBairro(), e.getLogradouro()
                    );

                    return new DenunciaDTO(
                            d.getId(), d.getTitulo(), d.getDescricao(),
                            d.getDataCriacao(), d.getDataAtualizacao(),
                            usuarioDto,
                            enderecoDto
                    );
                });
    }
}
