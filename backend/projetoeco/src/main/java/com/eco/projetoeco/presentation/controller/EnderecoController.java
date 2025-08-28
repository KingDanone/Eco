package com.eco.projetoeco.presentation.controller;

import com.eco.projetoeco.presentation.dto.EnderecoDTO;
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
    public ResponseEntity<EnderecoDTO> criar(@RequestBody @Valid EnderecoDTO dto) {
        EnderecoDTO criado = service.criarEndereco(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @DeleteMapping("/{cep}")
    public ResponseEntity<Void> deletar(@PathVariable String cep) {
        service.deletarPorCep(cep);
        return ResponseEntity.noContent().build();
    }
}
