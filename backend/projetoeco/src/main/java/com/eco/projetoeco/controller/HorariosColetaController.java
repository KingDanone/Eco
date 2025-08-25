package com.eco.projetoeco.controller;

import com.eco.projetoeco.dto.HorariosColetaDto;
import com.eco.projetoeco.dto.HorariosColetaRequestDto;
import com.eco.projetoeco.service.HorariosColetaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horarios-coleta")
public class HorariosColetaController {

    private final HorariosColetaService service;

    public HorariosColetaController(HorariosColetaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HorariosColetaDto> criar(@RequestBody @Valid HorariosColetaRequestDto request) {
        HorariosColetaDto criado = service.criar(request);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<HorariosColetaDto>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorariosColetaDto> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
