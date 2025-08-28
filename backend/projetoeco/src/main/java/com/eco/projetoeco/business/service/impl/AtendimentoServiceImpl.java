package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.presentation.dto.AtendimentoDTO;
import com.eco.projetoeco.presentation.dto.DenunciaDTO;
import com.eco.projetoeco.presentation.dto.UsuarioDTO;
import com.eco.projetoeco.data.model.Atendimento;
import com.eco.projetoeco.data.model.Denuncia;
import com.eco.projetoeco.data.model.Usuario;
import com.eco.projetoeco.data.repository.AtendimentoRepository;
import com.eco.projetoeco.data.repository.DenunciaRepository;
import com.eco.projetoeco.data.repository.UsuarioRepository;
import com.eco.projetoeco.business.service.AtendimentoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import static com.eco.projetoeco.business.mapper.ObjectMapper.parseObject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DenunciaRepository denunciaRepository;

    public AtendimentoServiceImpl(
            AtendimentoRepository atendimentoRepository,
            UsuarioRepository usuarioRepository,
            DenunciaRepository denunciaRepository) {
        this.atendimentoRepository = atendimentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.denunciaRepository = denunciaRepository;
    }

    @Override
    @Transactional
    public AtendimentoDTO criar(AtendimentoDTO requestDto) {
        Usuario usuario = usuarioRepository.findByCpf(requestDto.getUsuario().getCpf())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Denuncia denuncia = denunciaRepository.findById(requestDto.getDenuncia().getId())
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada"));

        Atendimento atendimento = new Atendimento();
        atendimento.setDataAtendimento(requestDto.getDataAtendimento());
        atendimento.setUsuario(usuario);
        atendimento.setDenuncia(denuncia);

        Atendimento salvo = atendimentoRepository.save(atendimento);

        UsuarioDTO usuarioDto = parseObject(usuario, UsuarioDTO.class);

        DenunciaDTO denunciaDto = new DenunciaDTO(
                denuncia.getId(),
                denuncia.getTitulo(),
                denuncia.getDescricao(),
                denuncia.getDataCriacao(),
                denuncia.getDataAtualizacao(),
                null,
                null
        );

        return new AtendimentoDTO(
                salvo.getProtocolo(),
                salvo.getDataAtendimento(),
                salvo.getStatus(),
                usuarioDto,
                denunciaDto
        );
    }

    @Override
    public List<AtendimentoDTO> listarTodos() {
        return atendimentoRepository.findAll().stream().map(atendimento -> {
            Usuario usuario = atendimento.getUsuario();
            Denuncia denuncia = atendimento.getDenuncia();

            return new AtendimentoDTO(
                    atendimento.getProtocolo(),
                    atendimento.getDataAtendimento(),
                    atendimento.getStatus(),
                    com.eco.projetoeco.business.mapper.ObjectMapper.parseObject(usuario, UsuarioDTO.class),
                    new DenunciaDTO(
                            denuncia.getId(),
                            denuncia.getTitulo(),
                            denuncia.getDescricao(),
                            denuncia.getDataCriacao(),
                            denuncia.getDataAtualizacao(),
                            null,
                            null
                    )
            );
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<AtendimentoDTO> buscarPorId(Long protocolo) {
        return atendimentoRepository.findById(protocolo).map(atendimento -> {
            Usuario usuario = atendimento.getUsuario();
            Denuncia denuncia = atendimento.getDenuncia();

            return new AtendimentoDTO(
                    atendimento.getProtocolo(),
                    atendimento.getDataAtendimento(),
                    atendimento.getStatus(),
                    parseObject(usuario, UsuarioDTO.class),
                    new DenunciaDTO(
                            denuncia.getId(),
                            denuncia.getTitulo(),
                            denuncia.getDescricao(),
                            denuncia.getDataCriacao(),
                            denuncia.getDataAtualizacao(),
                            null,
                            null
                    )
            );
        });
    }

    @Override
    public void deletar(Long protocolo) {
        atendimentoRepository.deleteById(protocolo);
    }
}
