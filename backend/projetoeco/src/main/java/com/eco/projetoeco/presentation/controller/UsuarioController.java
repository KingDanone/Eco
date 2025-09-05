package com.eco.projetoeco.presentation.controller;

import com.eco.projetoeco.business.service.UsuarioService;
import com.eco.projetoeco.presentation.dto.LoginRequestDTO;
import com.eco.projetoeco.presentation.dto.UsuarioDTO;
import com.eco.projetoeco.presentation.dto.UsuarioSenhaDTO;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        try {
            UsuarioDTO usuario = service.autenticar(loginRequest.getIdentifier(), loginRequest.getSenha());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<UsuarioDTO> editar(@PathVariable String cpf, @Valid @RequestBody UsuarioDTO dto) {
        UsuarioDTO usuarioAtualizado = service.editar(cpf, dto);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @PutMapping("/{cpf}/senha")
    public ResponseEntity<Void> alterarSenha(@PathVariable String cpf,
                                             @Valid @RequestBody UsuarioSenhaDTO dto) {
        service.alterarSenha(cpf, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UsuarioDTO> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.buscarPorCpf(cpf));
    }
}
