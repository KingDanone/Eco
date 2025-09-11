# Projeto Eco - Backend

Este é o backend do Projeto Eco, uma aplicação para registrar e gerenciar denúncias ambientais.

## Tecnologias Utilizadas

*   **Java 21**
*   **Spring Boot 3**
*   **Maven**
*   **MySQL**
*   **JPA (Hibernate)**
*   **MapStruct**
*   **Springdoc OpenAPI (Swagger)**

## Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone <url-do-repositorio>
    ```
2.  **Configure o banco de dados:**
    *   Crie um banco de dados MySQL chamado `eco`.
    *   Altere as configurações de acesso ao banco de dados no arquivo `src/main/resources/application.yml`:
        ```yaml
        spring:
          datasource:
            url: jdbc:mysql://localhost:3306/eco?useTimezone=true&serverTimezone=UTC
            username: <seu-usuario>
            password: <sua-senha>
        ```
3.  **Execute a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```
4.  **Acesse a documentação da API (Swagger):**
    *   Abra o seu navegador e acesse: `http://localhost:8080/swagger-ui.html`

## Visão Geral da API

A API possui os seguintes endpoints principais:

*   **Autenticação:**
    *   `POST /auth/login`: Autentica um usuário e retorna um token JWT.
*   **Usuários:**
    *   `POST /usuarios`: Cria um novo usuário.
    *   `GET /usuarios`: Lista todos os usuários (Admin).
    *   `GET /usuarios/{cpf}`: Busca um usuário por CPF (Admin ou próprio usuário).
    *   `PUT /usuarios/{cpf}`: Atualiza um usuário (Admin ou próprio usuário).
    *   `PUT /usuarios/{cpf}/senha`: Altera a senha de um usuário (Admin ou próprio usuário).
*   **Denúncias:**
    *   `POST /denuncias`: Cria uma nova denúncia (User).
    *   `GET /denuncias`: Lista todas as denúncias (Admin).
    *   `GET /denuncias/buscar/{id}`: Busca uma denúncia por ID (Admin ou dono da denúncia).
    *   `PATCH /denuncias/{id}/status`: Atualiza o status de uma denúncia (Admin).
*   **Atendimentos:**
    *   `GET /atendimentos/denuncias`: Busca denúncias por status (Admin).
*   **Respostas:**
    *   `POST /respostas`: Adiciona uma resposta a um atendimento (Admin).
*   **Avaliações:**
    *   `POST /avaliacoes`: Cria uma nova avaliação para um atendimento (User).
    *   `GET /avaliacoes`: Lista todas as avaliações (Admin).
    *   `DELETE /avaliacoes/{id}`: Deleta uma avaliação (Admin).
*   **Horários de Coleta:**
    *   `POST /horarios-coleta`: Cria um novo horário de coleta (Admin).
    *   `GET /horarios-coleta`: Lista todos os horários de coleta.
    *   `GET /horarios-coleta/{id}`: Busca um horário de coleta por ID.
    *   `DELETE /horarios-coleta/{id}`: Deleta um horário de coleta (Admin).
*   **Jogos:**
    *   `POST /jogos`: Adiciona um novo jogo (Admin).
    *   `GET /jogos`: Lista todos os jogos.
    *   `GET /jogos/{id}`: Busca um jogo por ID.
    *   `DELETE /jogos/{id}`: Deleta um jogo (Admin).
*   **Endereços:**
    *   `GET /enderecos`: Lista todos os endereços (Admin).

Para mais detalhes sobre os endpoints e os DTOs utilizados, consulte a documentação do Swagger.
