package com.eco.projetoeco.presentation.controller;

import com.eco.projetoeco.business.service.RespostaService;
import com.eco.projetoeco.presentation.dto.RespostaDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    private final RespostaService respostaService;

    public RespostaController(RespostaService respostaService) {
        this.respostaService = respostaService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RespostaDTO> criarResposta(@Valid @RequestBody RespostaDTO dto) {
        return ResponseEntity.ok(respostaService.criarResposta(dto));
    }
}
