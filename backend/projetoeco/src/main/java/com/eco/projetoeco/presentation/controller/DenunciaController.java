package com.eco.projetoeco.presentation.controller;


import com.eco.projetoeco.presentation.dto.DenunciaDTO;
import com.eco.projetoeco.business.service.DenunciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    private final DenunciaService denunciaService;

    public DenunciaController(DenunciaService denunciaService) {this.denunciaService = denunciaService;}

    @PostMapping
    public ResponseEntity<DenunciaDTO> criar(
            @RequestBody @Valid DenunciaDTO dto){
        DenunciaDTO criada = denunciaService.criarDenuncia(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping
    public ResponseEntity<List<DenunciaDTO>> listar(){
        return ResponseEntity.ok(denunciaService.listarTodas());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<DenunciaDTO> buscarPorId(@PathVariable Long id){
        return denunciaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}