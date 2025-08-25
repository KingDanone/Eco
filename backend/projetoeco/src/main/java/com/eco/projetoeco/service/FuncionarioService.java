package com.eco.projetoeco.service;

import com.eco.projetoeco.dto.FuncionarioDto;
import com.eco.projetoeco.dto.FuncionarioRequestDto;

import java.util.List;
import java.util.Optional;

public interface FuncionarioService {
    FuncionarioDto criar(FuncionarioRequestDto requestDto);
    List<FuncionarioDto> listarTodos();
    Optional<FuncionarioDto> buscarPorCodigo(String codigo);
}
