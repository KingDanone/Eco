package com.eco.projetoeco.presentation.controller;


import com.eco.projetoeco.presentation.dto.denunciadto.DenunciaDTO;
import com.eco.projetoeco.business.service.DenunciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.eco.projetoeco.presentation.dto.denunciadto.UpdateDenunciaStatusDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import com.eco.projetoeco.presentation.dto.denunciadto.EditarDenunciaDTO;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    private final DenunciaService denunciaService;

    public DenunciaController(DenunciaService denunciaService) {this.denunciaService = denunciaService;}

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DenunciaDTO> criar(
            @RequestBody @Valid DenunciaDTO dto,
            @AuthenticationPrincipal UserDetails userDetails){
        DenunciaDTO criada = denunciaService.criarDenuncia(dto, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DenunciaDTO>> listar(){
        return ResponseEntity.ok(denunciaService.listarTodas());
    }

    @GetMapping("/minhas-denuncias")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<DenunciaDTO>> listarPorUsuario(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(denunciaService.listarPorUsuario(userDetails));
    }

    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasRole('ADMIN') or @denunciaServiceImpl.isOwner(#id, authentication.principal.username)")
    public ResponseEntity<DenunciaDTO> buscarPorId(@PathVariable Long id){
        return denunciaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') and @denunciaServiceImpl.isOwner(#id, authentication.principal.username)")
    public ResponseEntity<DenunciaDTO> editarDenuncia(@PathVariable Long id,
                                                    @RequestBody @Valid EditarDenunciaDTO dados,
                                                    @AuthenticationPrincipal UserDetails userDetails) {
        DenunciaDTO atualizada = denunciaService.editarDenuncia(id, dados, userDetails);
        return ResponseEntity.ok(atualizada);
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DenunciaDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestBody @Valid UpdateDenunciaStatusDTO statusDTO) {
        DenunciaDTO atualizada = denunciaService.atualizarStatus(id, statusDTO);
        return ResponseEntity.ok(atualizada);
    }
}