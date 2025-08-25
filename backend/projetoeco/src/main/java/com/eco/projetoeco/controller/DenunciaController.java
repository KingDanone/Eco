package com.eco.projetoeco.controller;


import com.eco.projetoeco.dto.DenunciaDto;
import com.eco.projetoeco.dto.DenunciaRequestDto;
import com.eco.projetoeco.service.DenunciaService;
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
    public ResponseEntity<DenunciaDto> criar(
            @RequestBody @Valid DenunciaRequestDto requestDto){
        DenunciaDto criada = denunciaService.criarDenuncia(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping
    public ResponseEntity<List<DenunciaDto>> listar(){
        return ResponseEntity.ok(denunciaService.listarTodas());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<DenunciaDto> buscarPorId(@PathVariable Long id){
        return denunciaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}