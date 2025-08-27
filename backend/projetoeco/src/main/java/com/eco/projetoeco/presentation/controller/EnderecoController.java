package com.eco.projetoeco.presentation.controller;

import com.eco.projetoeco.presentation.dto.EnderecoDto;
import com.eco.projetoeco.presentation.dto.EnderecoRequestDto;
import com.eco.projetoeco.business.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EnderecoDto> criar(@RequestBody @Valid EnderecoRequestDto dto) {
        EnderecoDto criado = service.criarEndereco(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @DeleteMapping("/{cep}")
    public ResponseEntity<Void> deletar(@PathVariable String cep) {
        service.deletarPorCep(cep);
        return ResponseEntity.noContent().build();
    }
}
