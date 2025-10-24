package com.eco.projetoeco.presentation.controller;

import com.eco.projetoeco.business.mapper.UsuarioMapper;
import com.eco.projetoeco.infrastructure.security.JwtService;
import com.eco.projetoeco.domain.model.Usuario;
import com.eco.projetoeco.presentation.dto.LoginRequestDTO;
import com.eco.projetoeco.presentation.dto.LoginResponseDTO;
import com.eco.projetoeco.presentation.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        log.info("Tentativa de login para o identificador: {}", request.getIdentifier());
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getIdentifier(), request.getSenha())
        );
        log.info("Autenticação bem-sucedida para: {}", request.getIdentifier());

        var user = (Usuario) authentication.getPrincipal();
        log.info("Usuário principal obtido: {}", user.getEmail());

        final String token = jwtService.generateToken(user);
        log.info("Token gerado com sucesso.");

        final UsuarioDTO usuarioDTO = usuarioMapper.toDTO(user);
        log.info("DTO do usuário mapeado com sucesso: {}", usuarioDTO.getEmail());

        LoginResponseDTO response = new LoginResponseDTO(token, usuarioDTO);
        log.info("Resposta de login pronta para ser enviada.");

        return ResponseEntity.ok(response);
    }
}
