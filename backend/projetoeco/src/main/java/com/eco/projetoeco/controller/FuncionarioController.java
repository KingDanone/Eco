package com.eco.projetoeco.controller;

import com.eco.projetoeco.dto.FuncionarioDto;
import com.eco.projetoeco.dto.FuncionarioRequestDto;
import com.eco.projetoeco.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioDto> criar(@RequestBody @Valid FuncionarioRequestDto requestDto) {
        FuncionarioDto criado = funcionarioService.criar(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> listarTodos() {
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<FuncionarioDto> buscarPorCodigo(@PathVariable String codigo) {
        return funcionarioService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
