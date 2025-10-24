package com.eco.projetoeco.infrastructure;

import com.eco.projetoeco.domain.model.enuns.UserRole;
import com.eco.projetoeco.domain.repository.UsuarioRepository;
import com.eco.projetoeco.business.service.UsuarioService;
import com.eco.projetoeco.presentation.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminUserInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Verificando se um usuário ADMIN existe...");

        if (!usuarioRepository.existsByRole(UserRole.ADMIN)) {
            log.info("Nenhum usuário ADMIN encontrado. Criando um novo usuário administrador.");

            UsuarioDTO adminDto = UsuarioDTO.builder()
                    .nome("Administrador")
                    .nickname("admin")
                    .email("admin@eco.com")
                    .cpf("00000000000") // CPF genérico, pode ser ajustado
                    .senha("admin1234") // Senha provisória. Recomenda-se alterar no primeiro login.
                    .build();

            try {
                usuarioService.criarAdmin(adminDto);
                log.info("Usuário administrador criado com sucesso.");
            } catch (Exception e) {
                log.error("Falha ao criar usuário administrador: {}", e.getMessage());
            }
        } else {
            log.info("Usuário ADMIN já existe. Nenhuma ação necessária.");
        }
    }
}
