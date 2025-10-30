package com.eco.projetoeco.presentation.controller;

import com.eco.projetoeco.business.service.UsuarioService;
import com.eco.projetoeco.presentation.dto.LoginRequestDTO;
import com.eco.projetoeco.presentation.dto.UsuarioDTO;
import com.eco.projetoeco.presentation.dto.UsuarioSenhaDTO;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{cpf}")
    @PreAuthorize("hasRole('ADMIN') or #cpf == authentication.principal.cpf")
    public ResponseEntity<UsuarioDTO> editar(@PathVariable String cpf, @Valid @RequestBody UsuarioDTO dto) {
        UsuarioDTO usuarioAtualizado = service.editar(cpf, dto);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @PutMapping("/{cpf}/senha")
    @PreAuthorize("hasRole('ADMIN') or #cpf == authentication.principal.cpf")
    public ResponseEntity<Void> alterarSenha(@PathVariable String cpf,
                                             @Valid @RequestBody UsuarioSenhaDTO dto) {
        service.alterarSenha(cpf, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cpf}")
    @PreAuthorize("hasRole('ADMIN') or #cpf == authentication.principal.cpf")
    public ResponseEntity<UsuarioDTO> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.buscarPorCpf(cpf));
    }
}
