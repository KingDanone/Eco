package com.eco.projetoeco.presentation.controller;

import com.eco.projetoeco.data.model.enuns.StatusDenuncia;
import com.eco.projetoeco.business.service.AtendimentoService;
import com.eco.projetoeco.presentation.dto.DenunciaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {

    private final AtendimentoService service;

    public AtendimentoController(AtendimentoService service) {
        this.service = service;
    }

    @GetMapping("/denuncias")
    public ResponseEntity<List<DenunciaDTO>> buscarDenuncias(@RequestParam(required = false) StatusDenuncia status) {
        return ResponseEntity.ok(service.buscarDenuncias(status));
    }
}
