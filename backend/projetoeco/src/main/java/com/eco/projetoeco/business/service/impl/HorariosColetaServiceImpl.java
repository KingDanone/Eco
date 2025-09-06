package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.mapper.HorariosColetaMapper;
import com.eco.projetoeco.presentation.dto.HorariosColetaDTO;
import com.eco.projetoeco.data.model.Endereco;
import com.eco.projetoeco.data.model.HorariosColeta;
import com.eco.projetoeco.data.repository.EnderecoRepository;
import com.eco.projetoeco.data.repository.HorariosColetaRepository;
import com.eco.projetoeco.business.service.HorariosColetaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorariosColetaServiceImpl implements HorariosColetaService {

    private final HorariosColetaRepository repository;
    private final EnderecoRepository enderecoRepository;
    private final HorariosColetaMapper mapper;

    public HorariosColetaServiceImpl(HorariosColetaRepository repository,
                                     EnderecoRepository enderecoRepository,
                                     HorariosColetaMapper mapper) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public HorariosColetaDTO criar(HorariosColetaDTO request) {
        Endereco endereco = enderecoRepository.findByCep(request.getEnderecoCep())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        HorariosColeta coleta = mapper.toEntity(request);
        coleta.setEndereco(endereco);

        HorariosColeta salvo = repository.save(coleta);

        return mapper.toDTO(salvo);
    }

    @Override
    public List<HorariosColetaDTO> listarTodos() {
        return mapper.toDTO(repository.findAll());
    }

    @Override
    public Optional<HorariosColetaDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
