package com.eco.projetoeco.service.impl;

import com.eco.projetoeco.dto.AtendimentoDto;
import com.eco.projetoeco.dto.AtendimentoRequestDto;
import com.eco.projetoeco.dto.DenunciaResumoDto;
import com.eco.projetoeco.dto.FuncionarioResumoDto;
import com.eco.projetoeco.model.Atendimento;
import com.eco.projetoeco.model.Denuncia;
import com.eco.projetoeco.model.Funcionario;
import com.eco.projetoeco.repository.AtendimentoRepository;
import com.eco.projetoeco.repository.DenunciaRepository;
import com.eco.projetoeco.repository.FuncionarioRepository;
import com.eco.projetoeco.service.AtendimentoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final DenunciaRepository denunciaRepository;

    public AtendimentoServiceImpl(
            AtendimentoRepository atendimentoRepository,
            FuncionarioRepository funcionarioRepository,
            DenunciaRepository denunciaRepository) {
        this.atendimentoRepository = atendimentoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.denunciaRepository = denunciaRepository;
    }

    @Override
    @Transactional
    public AtendimentoDto criar(AtendimentoRequestDto requestDto) {
        Funcionario funcionario = funcionarioRepository.findById(requestDto.getFuncionarioCodigo())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        Denuncia denuncia = denunciaRepository.findById(requestDto.getDenunciaId())
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada"));

        Atendimento atendimento = new Atendimento();
        atendimento.setDataAtendimento(requestDto.getDataAtendimento());
//        atendimento.setStatus(requestDto.getStatus());
        atendimento.setFuncionario(funcionario);
        atendimento.setDenuncia(denuncia);

        Atendimento salvo = atendimentoRepository.save(atendimento);

        FuncionarioResumoDto funcionarioDto = new FuncionarioResumoDto(
                funcionario.getCodigo(),
                funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getTelefone()
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
                funcionarioDto,
                denunciaDto
        );
    }

    @Override
    public List<AtendimentoDto> listarTodos() {
        return atendimentoRepository.findAll().stream().map(atendimento -> {
            Funcionario funcionario = atendimento.getFuncionario();
            Denuncia denuncia = atendimento.getDenuncia();

            return new AtendimentoDto(
                    atendimento.getProtocolo(),
                    atendimento.getDataAtendimento(),
                    atendimento.getStatus(),
                    new FuncionarioResumoDto(
                            funcionario.getCodigo(),
                            funcionario.getNome(),
                            funcionario.getEmail(),
                            funcionario.getTelefone()
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
            Funcionario funcionario = atendimento.getFuncionario();
            Denuncia denuncia = atendimento.getDenuncia();

            return new AtendimentoDto(
                    atendimento.getProtocolo(),
                    atendimento.getDataAtendimento(),
                    atendimento.getStatus(),
                    new FuncionarioResumoDto(
                            funcionario.getCodigo(),
                            funcionario.getNome(),
                            funcionario.getEmail(),
                            funcionario.getTelefone()
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
