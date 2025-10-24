package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.exception.ResourceNotFoundException;
import com.eco.projetoeco.business.mapper.AnexoMapper;
import com.eco.projetoeco.business.mapper.DenunciaMapper;
import com.eco.projetoeco.business.mapper.EnderecoMapper;
import com.eco.projetoeco.business.storage.StorageService;
import com.eco.projetoeco.presentation.dto.denunciadto.DenunciaDTO;
import com.eco.projetoeco.domain.model.Anexo;
import com.eco.projetoeco.domain.model.Denuncia;
import com.eco.projetoeco.domain.model.Endereco;
import com.eco.projetoeco.domain.model.Usuario;
import com.eco.projetoeco.domain.repository.AnexoRepository;
import com.eco.projetoeco.domain.repository.DenunciaRepository;
import com.eco.projetoeco.domain.repository.EnderecoRepository;
import com.eco.projetoeco.domain.repository.UsuarioRepository;
import com.eco.projetoeco.business.service.DenunciaService;
import com.eco.projetoeco.presentation.dto.denunciadto.UpdateDenunciaStatusDTO;
import com.eco.projetoeco.domain.model.enuns.StatusDenuncia;
import com.eco.projetoeco.presentation.dto.denunciadto.EditarDenunciaDTO;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class DenunciaServiceImpl implements DenunciaService {

    private final DenunciaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final DenunciaMapper denunciaMapper;
    private final EnderecoMapper enderecoMapper;
    private final StorageService storageService;
    private final AnexoRepository anexoRepository;
    private final AnexoMapper anexoMapper;

    public DenunciaServiceImpl(DenunciaRepository repository,
                               UsuarioRepository usuarioRepository,
                               EnderecoRepository enderecoRepository,
                               DenunciaMapper denunciaMapper,
                               EnderecoMapper enderecoMapper,
                               StorageService storageService,
                               AnexoRepository anexoRepository,
                               AnexoMapper anexoMapper) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
        this.denunciaMapper = denunciaMapper;
        this.enderecoMapper = enderecoMapper;
        this.storageService = storageService;
        this.anexoRepository = anexoRepository;
        this.anexoMapper = anexoMapper;
    }

    @Override
    @Transactional
    public DenunciaDTO criarDenuncia(DenunciaDTO request, MultipartFile anexoFile, UserDetails userDetails) {
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

        // Salvar a nova denuncia (primeiro para obter o ID)
        Denuncia salva = repository.save(denuncia);

        // Processar o anexo, se existir
        if (anexoFile != null && !anexoFile.isEmpty()) {
            // Gerar nome único e caminho de armazenamento
            String extensao = getFileExtension(anexoFile.getOriginalFilename());
            String nomeUnico = "denuncia_" + salva.getId() + "_" + System.currentTimeMillis() + "." + extensao;
            String caminhoRelativo = "denuncias/usuario_" + usuario.getId() + "/" + nomeUnico;

            // Armazenar o arquivo
            String caminhoArmazenamento = storageService.armazenar(anexoFile, caminhoRelativo);

            // Criar e associar a entidade Anexo
            Anexo anexo = new Anexo();
            anexo.setNomeOriginal(anexoFile.getOriginalFilename());
            anexo.setNomeArmazenado(nomeUnico);
            anexo.setCaminhoArmazenado(caminhoArmazenamento);
            anexo.setContentType(anexoFile.getContentType());
            anexo.setTamanhoBytes(anexoFile.getSize());
            anexo.setDenuncia(salva); // Associar o anexo à denúncia

            anexoRepository.save(anexo); // Salvar o anexo

            salva.setAnexo(anexo); // Associar o anexo à denúncia
            repository.save(salva); // Atualizar a denúncia com a referência ao anexo
        }

        // Retornar o DTO da denuncia salva
        return denunciaMapper.toDTO(salva);
    }

    // Método auxiliar para extrair a extensão do arquivo
    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf('.') == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf('.') + 1);
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
        return repository.findByIdWithAssociations(id)
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
        return repository.findByIdWithAssociations(id)
                .map(denuncia -> denuncia.getUsuario().getEmail().equals(username))
                .orElse(false);
    }
}
