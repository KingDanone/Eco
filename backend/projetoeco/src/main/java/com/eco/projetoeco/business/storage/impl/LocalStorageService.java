package com.eco.projetoeco.business.storage.impl;

import com.eco.projetoeco.business.storage.StorageService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class LocalStorageService implements StorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private Path fileStorageLocation;

    @PostConstruct
    public void init() {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
            log.info("Diretório de armazenamento de arquivos inicializado: {}", this.fileStorageLocation);
        } catch (Exception ex) {
            log.error("Não foi possível criar o diretório de armazenamento de arquivos: {}", this.fileStorageLocation, ex);
            throw new RuntimeException("Não foi possível criar o diretório de armazenamento de arquivos.", ex);
        }
    }

    @Override
    public String armazenar(MultipartFile arquivo, String caminhoDesejado) {
        try {
            if (arquivo.isEmpty()) {
                throw new RuntimeException("Falha ao armazenar arquivo vazio.");
            }

            Path destinoArquivo = this.fileStorageLocation.resolve(caminhoDesejado).normalize();

            // Garante que o diretório pai exista
            Files.createDirectories(destinoArquivo.getParent());

            Files.copy(arquivo.getInputStream(), destinoArquivo, StandardCopyOption.REPLACE_EXISTING);
            log.info("Arquivo '{}' armazenado em '{}'", arquivo.getOriginalFilename(), destinoArquivo);
            return caminhoDesejado; // Retorna o caminho relativo para ser salvo no banco
        } catch (IOException ex) {
            log.error("Falha ao armazenar o arquivo '{}'", arquivo.getOriginalFilename(), ex);
            throw new RuntimeException("Falha ao armazenar o arquivo.", ex);
        }
    }

    @Override
    public Resource recuperarComoRecurso(String caminhoArquivo) {
        try {
            Path filePath = this.fileStorageLocation.resolve(caminhoArquivo).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Arquivo não encontrado ou não legível: " + caminhoArquivo);
            }
        } catch (MalformedURLException ex) {
            log.error("Erro ao recuperar recurso para o caminho '{}'", caminhoArquivo, ex);
            throw new RuntimeException("Erro ao recuperar recurso.", ex);
        }
    }

    @Override
    public Path recuperarComoPath(String caminhoArquivo) {
        return this.fileStorageLocation.resolve(caminhoArquivo).normalize();
    }

    @Override
    public void deletar(String caminhoArquivo) {
        try {
            Path filePath = this.fileStorageLocation.resolve(caminhoArquivo).normalize();
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                log.info("Arquivo '{}' deletado com sucesso.", caminhoArquivo);
            } else {
                log.warn("Tentativa de deletar arquivo inexistente: '{}'", caminhoArquivo);
            }
        } catch (IOException ex) {
            log.error("Falha ao deletar o arquivo '{}'", caminhoArquivo, ex);
            throw new RuntimeException("Falha ao deletar o arquivo.", ex);
        }
    }
}
