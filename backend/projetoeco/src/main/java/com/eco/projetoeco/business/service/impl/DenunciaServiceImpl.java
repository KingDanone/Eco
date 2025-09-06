package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.mapper.DenunciaMapper;
import com.eco.projetoeco.business.mapper.EnderecoMapper;
import com.eco.projetoeco.presentation.dto.DenunciaDTO;
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

@Service
public class DenunciaServiceImpl implements DenunciaService {

    private final DenunciaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final DenunciaMapper denunciaMapper;
    private final EnderecoMapper enderecoMapper;

    public DenunciaServiceImpl(DenunciaRepository repository,
                               UsuarioRepository usuarioRepository,
                               EnderecoRepository enderecoRepository,
                               DenunciaMapper denunciaMapper,
                               EnderecoMapper enderecoMapper) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
        this.denunciaMapper = denunciaMapper;
        this.enderecoMapper = enderecoMapper;
    }

    @Override
    @Transactional
    public DenunciaDTO criarDenuncia(DenunciaDTO request) {
        Usuario usuario = usuarioRepository.findByCpf(request.getUsuario().getCpf())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Endereco endereco = enderecoMapper.toEntity(request.getEndereco());
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        Denuncia denuncia = denunciaMapper.toEntity(request);
        denuncia.setUsuario(usuario);
        denuncia.setEndereco(enderecoSalvo);


        Denuncia salva = repository.save(denuncia);

        return denunciaMapper.toDTO(salva);
    }

    @Override
    public List<DenunciaDTO> listarTodas() {
        return denunciaMapper.toDTO(repository.findAllWithAssociations());
    }

    @Override
    public Optional<DenunciaDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(denunciaMapper::toDTO);
    }
}
