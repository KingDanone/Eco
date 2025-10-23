package com.eco.projetoeco.business.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    /**
     * Armazena um arquivo.
     *
     * @param arquivo O arquivo a ser armazenado.
     * @param caminhoDesejado O caminho relativo (incluindo o nome do arquivo) onde o arquivo deve ser armazenado.
     *                        Ex: "denuncias/usuario_1/denuncia_101_timestamp.jpg"
     * @return O caminho completo (URL ou path absoluto) onde o arquivo foi armazenado.
     */
    String armazenar(MultipartFile arquivo, String caminhoDesejado);

    /**
     * Carrega um arquivo como um recurso.
     *
     * @param caminhoArquivo O caminho relativo (incluindo o nome do arquivo) do arquivo a ser carregado.
     * @return O recurso do arquivo.
     */
    Resource recuperarComoRecurso(String caminhoArquivo);

    /**
     * Carrega um arquivo como um objeto Path.
     *
     * @param caminhoArquivo O caminho relativo (incluindo o nome do arquivo) do arquivo a ser carregado.
     * @return O Path do arquivo.
     */
    Path recuperarComoPath(String caminhoArquivo);

    /**
     * Deleta um arquivo.
     *
     * @param caminhoArquivo O caminho relativo (incluindo o nome do arquivo) do arquivo a ser deletado.
     */
    void deletar(String caminhoArquivo);

    /**
     * Inicializa o diretório de armazenamento, se necessário.
     */
    void init();
}
