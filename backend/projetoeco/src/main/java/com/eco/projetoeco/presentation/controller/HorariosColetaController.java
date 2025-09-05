package com.eco.projetoeco.presentation.controller;

import com.eco.projetoeco.presentation.dto.HorariosColetaDTO;
import com.eco.projetoeco.business.service.HorariosColetaService;
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
    public ResponseEntity<HorariosColetaDTO> criar(@RequestBody @Valid HorariosColetaDTO dto) {
        HorariosColetaDTO criado = service.criar(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<HorariosColetaDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorariosColetaDTO> buscarPorId(@PathVariable Long id) {
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
