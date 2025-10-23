package com.eco.projetoeco.presentation.controller;

import com.eco.projetoeco.business.storage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final StorageService storageService;

    @GetMapping("/denuncias/{filename:.+}")
    @PreAuthorize("isAuthenticated()") // Ou pode ser 'hasRole('ADMIN')' ou uma lógica mais complexa
    public ResponseEntity<Resource> serveDenunciaAnexo(@PathVariable String filename, HttpServletRequest request) {
        log.info("Tentativa de servir anexo de denúncia: {}", filename);
        Resource resource = storageService.recuperarComoRecurso("denuncias/" + filename);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.warn("Não foi possível determinar o tipo de conteúdo do arquivo: {}", filename, ex);
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
