package com.eco.projetoeco.presentation.controller;

import com.eco.projetoeco.business.mapper.UsuarioMapper;
import com.eco.projetoeco.business.security.JwtService;
import com.eco.projetoeco.data.model.Usuario;
import com.eco.projetoeco.presentation.dto.LoginRequestDTO;
import com.eco.projetoeco.presentation.dto.LoginResponseDTO;
import com.eco.projetoeco.presentation.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getIdentifier(), request.getSenha())
        );

        var user = (Usuario) authentication.getPrincipal();
        final String token = jwtService.generateToken(user);
        final UsuarioDTO usuarioDTO = usuarioMapper.toDTO(user);

        return ResponseEntity.ok(new LoginResponseDTO(token, usuarioDTO));
    }
}
