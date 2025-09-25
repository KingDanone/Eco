package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.exception.ResourceNotFoundException;
import com.eco.projetoeco.business.mapper.DenunciaMapper;
import com.eco.projetoeco.business.mapper.EnderecoMapper;
import com.eco.projetoeco.presentation.dto.denunciadto.DenunciaDTO;
import com.eco.projetoeco.data.model.Denuncia;
import com.eco.projetoeco.data.model.Endereco;
import com.eco.projetoeco.data.model.Usuario;
import com.eco.projetoeco.data.repository.DenunciaRepository;
import com.eco.projetoeco.data.repository.EnderecoRepository;
import com.eco.projetoeco.data.repository.UsuarioRepository;
import com.eco.projetoeco.business.service.DenunciaService;
import com.eco.projetoeco.presentation.dto.denunciadto.UpdateDenunciaStatusDTO;
import com.eco.projetoeco.data.model.enuns.StatusDenuncia;
import com.eco.projetoeco.presentation.dto.denunciadto.EditarDenunciaDTO;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
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
    public DenunciaDTO criarDenuncia(DenunciaDTO request, UserDetails userDetails) {
        // Buscar Usuario pelo email do UserDetails
        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com email " + userDetails.getUsername() + " não encontrado"));

        // Mapear, salvar e associar o novo Endereco
        Endereco endereco = enderecoMapper.toEntity(request.getEndereco());
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        // Mapear o DTO para a entidade Denuncia
        Denuncia denuncia = denunciaMapper.toEntity(request);
        denuncia.setUsuario(usuario);
        denuncia.setEndereco(enderecoSalvo);

        // Salvar a nova denuncia
        Denuncia salva = repository.save(denuncia);

        // Retornar o DTO da denuncia salva
        return denunciaMapper.toDTO(salva);
    }

    @Override
    public List<DenunciaDTO> listarTodas() {
        return denunciaMapper.toDTO(repository.findAllWithAssociations());
    }

    @Override
    public List<DenunciaDTO> listarPorUsuario(UserDetails userDetails) {
        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com email " + userDetails.getUsername() + " não encontrado"));

        return denunciaMapper.toDTO(repository.findByUsuarioId(usuario.getId()));
    }

    @Override
    public Optional<DenunciaDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(denunciaMapper::toDTO);
    }

    @Override
    @Transactional
    public DenunciaDTO editarDenuncia(Long id, EditarDenunciaDTO dados, UserDetails userDetails) {
        Denuncia denuncia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Denúncia com id " + id + " não encontrada."));

        if (!denuncia.getUsuario().getEmail().equals(userDetails.getUsername())) {
            throw new AccessDeniedException("Você não tem permissão para editar esta denúncia.");
        }

        if (denuncia.getStatus() != StatusDenuncia.ABERTA) {
            throw new IllegalStateException("Apenas denúncias com status 'ABERTA' podem ser editadas.");
        }

        denuncia.setTitulo(dados.getTitulo());
        denuncia.setDescricao(dados.getDescricao());

        Denuncia salva = repository.save(denuncia);

        return denunciaMapper.toDTO(salva);
    }

    @Override
    @Transactional
    public DenunciaDTO atualizarStatus(Long id, UpdateDenunciaStatusDTO statusDTO) {
        Denuncia denuncia = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Denúncia com id " + id + " não encontrada."));

        denuncia.setStatus(statusDTO.getStatus());

        Denuncia salva = repository.save(denuncia);

        return denunciaMapper.toDTO(salva);
    }

    @Override
    public boolean isOwner(Long id, String username) {
        return repository.findById(id)
                .map(denuncia -> denuncia.getUsuario().getEmail().equals(username))
                .orElse(false);
    }
}
