package com.eco.projetoeco.controller;

import com.eco.projetoeco.dto.*;
import com.eco.projetoeco.service.UsuarioService;
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
    public ResponseEntity<UsuarioDto> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        try {
            UsuarioDto usuario = service.autenticar(loginRequest.getIdentifier(), loginRequest.getSenha());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> criar(@Valid @RequestBody UsuarioRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<UsuarioDto> editar(@PathVariable String cpf, @Valid @RequestBody UsuarioUpdateRequestDto dto) {
        UsuarioDto usuarioAtualizado = service.editar(cpf, dto);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @PutMapping("/{cpf}/senha")
    public ResponseEntity<Void> alterarSenha(@PathVariable String cpf,
                                             @Valid @RequestBody UsuarioSenhaUpdateDto dto) {
        service.alterarSenha(cpf, dto);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{cpf}")
    public ResponseEntity<UsuarioDto> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.buscarPorCpf(cpf));
    }
}
