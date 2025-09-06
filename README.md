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

*   **Usuários:**
    *   `POST /usuarios`: Cria um novo usuário.
    *   `GET /usuarios`: Lista todos os usuários.
    *   `GET /usuarios/{cpf}`: Busca um usuário por CPF.
    *   `PUT /usuarios/{cpf}`: Atualiza um usuário.
*   **Denúncias:**
    *   `POST /denuncias`: Cria uma nova denúncia.
    *   `GET /denuncias`: Lista todas as denúncias.
    *   `GET /denuncias/{id}`: Busca uma denúncia por ID.
*   **Atendimentos:**
    *   `POST /atendimentos`: Cria um novo atendimento para uma denúncia.
    *   `GET /atendimentos`: Lista todos os atendimentos.
    *   `GET /atendimentos/{protocolo}`: Busca um atendimento por protocolo.
*   **Respostas:**
    *   `POST /respostas`: Adiciona uma resposta a um atendimento.

Para mais detalhes sobre os endpoints e os DTOs utilizados, consulte a documentação do Swagger.
