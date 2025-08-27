package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.presentation.dto.AtendimentoDto;
import com.eco.projetoeco.presentation.dto.AtendimentoRequestDto;
import com.eco.projetoeco.presentation.dto.DenunciaResumoDto;
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
    public AtendimentoDto criar(AtendimentoRequestDto requestDto) {
        Usuario usuario = usuarioRepository.findById(requestDto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Denuncia denuncia = denunciaRepository.findById(requestDto.getDenunciaId())
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada"));

        Atendimento atendimento = new Atendimento();
        atendimento.setDataAtendimento(requestDto.getDataAtendimento());
//        atendimento.setStatus(requestDto.getStatus());
        atendimento.setUsuario(usuario);
        atendimento.setDenuncia(denuncia);

        Atendimento salvo = atendimentoRepository.save(atendimento);

        UsuarioDTO usuarioDto = new UsuarioDTO(
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getNickname(),
                usuario.getEmail(),
                usuario.getTelefone(),
                null
        );

        DenunciaResumoDto denunciaDto = new DenunciaResumoDto(
                denuncia.getId(),
                denuncia.getTitulo(),
                denuncia.getDescricao()
        );

        return new AtendimentoDto(
                salvo.getProtocolo(),
                salvo.getDataAtendimento(),
                salvo.getStatus(),
                usuarioDto,
                denunciaDto
        );
    }

    @Override
    public List<AtendimentoDto> listarTodos() {
        return atendimentoRepository.findAll().stream().map(atendimento -> {
            Usuario usuario = atendimento.getUsuario();
            Denuncia denuncia = atendimento.getDenuncia();

            return new AtendimentoDto(
                    atendimento.getProtocolo(),
                    atendimento.getDataAtendimento(),
                    atendimento.getStatus(),
                    new UsuarioDTO(
                            usuario.getCpf(),
                            usuario.getNome(),
                            usuario.getNickname(),
                            usuario.getEmail(),
                            usuario.getTelefone(),
                            null
                    ),
                    new DenunciaResumoDto(
                            denuncia.getId(),
                            denuncia.getTitulo(),
                            denuncia.getDescricao()
                    )
            );
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<AtendimentoDto> buscarPorId(Long protocolo) {
        return atendimentoRepository.findById(protocolo).map(atendimento -> {
            Usuario usuario = atendimento.getUsuario();
            Denuncia denuncia = atendimento.getDenuncia();

            return new AtendimentoDto(
                    atendimento.getProtocolo(),
                    atendimento.getDataAtendimento(),
                    atendimento.getStatus(),
                    new UsuarioDTO(
                            usuario.getCpf(),
                            usuario.getNome(),
                            usuario.getNickname(),
                            usuario.getEmail(),
                            usuario.getTelefone(),
                            null
                    ),
                    new DenunciaResumoDto(
                            denuncia.getId(),
                            denuncia.getTitulo(),
                            denuncia.getDescricao()
                    )
            );
        });
    }

    @Override
    public void deletar(Long protocolo) {
        atendimentoRepository.deleteById(protocolo);
    }
}
