package com.eco.projetoeco.presentation.controller;

import com.eco.projetoeco.presentation.dto.EnderecoDTO;
import com.eco.projetoeco.business.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    // Desabilitado: Endereços são criados apenas no fluxo de denúncia

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EnderecoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

}
