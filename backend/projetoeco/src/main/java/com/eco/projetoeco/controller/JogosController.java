package com.eco.projetoeco.controller;

import com.eco.projetoeco.dto.JogosDto;
import com.eco.projetoeco.dto.JogosRequestDto;
import com.eco.projetoeco.service.JogosService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogosController {

    private final JogosService service;

    public JogosController(JogosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<JogosDto> criar(@Valid @RequestBody JogosRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<JogosDto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogosDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
