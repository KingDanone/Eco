package com.eco.projetoeco.service.impl;

import com.eco.projetoeco.dto.HorariosColetaDto;
import com.eco.projetoeco.dto.HorariosColetaRequestDto;
import com.eco.projetoeco.model.Endereco;
import com.eco.projetoeco.model.HorariosColeta;
import com.eco.projetoeco.repository.EnderecoRepository;
import com.eco.projetoeco.repository.HorariosColetaRepository;
import com.eco.projetoeco.service.HorariosColetaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HorariosColetaServiceImpl implements HorariosColetaService {

    private final HorariosColetaRepository repository;
    private final EnderecoRepository enderecoRepository;

    public HorariosColetaServiceImpl(HorariosColetaRepository repository, EnderecoRepository enderecoRepository) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    @Transactional
    public HorariosColetaDto criar(HorariosColetaRequestDto request) {
        Endereco endereco = enderecoRepository.findById(request.getEnderecoCep())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        HorariosColeta coleta = new HorariosColeta();
        coleta.setDiaSemana(request.getDiaSemana());
        coleta.setTurno(request.getTurno());
        coleta.setEndereco(endereco);

        HorariosColeta salvo = repository.save(coleta);

        return new HorariosColetaDto(salvo.getId(), salvo.getDiaSemana(), salvo.getTurno(), salvo.getEndereco().getCep());
    }

    @Override
    public List<HorariosColetaDto> listarTodos() {
        return repository.findAll().stream()
                .map(h -> new HorariosColetaDto(
                        h.getId(), h.getDiaSemana(), h.getTurno(), h.getEndereco().getCep()
                )).collect(Collectors.toList());
    }

    @Override
    public Optional<HorariosColetaDto> buscarPorId(Long id) {
        return repository.findById(id)
                .map(h -> new HorariosColetaDto(
                        h.getId(), h.getDiaSemana(), h.getTurno(), h.getEndereco().getCep()
                ));
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
