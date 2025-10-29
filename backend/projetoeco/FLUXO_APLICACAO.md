# Fluxo de Uso da API Eco

Este documento descreve o fluxo de autenticação e os principais casos de uso da API, servindo como um guia para desenvolvedores frontend e para testes.

---

## Autenticação

A API utiliza **JSON Web Tokens (JWT)** para gerenciar a autenticação. Quase todos os endpoints são protegidos e exigem um token válido no cabeçalho `Authorization`.

- **Chave**: `Authorization`
- **Valor**: `Bearer <seu_token_jwt>`

**Exemplo:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvLnNpbHZhQGVtYWlsLmNvbSIsImlhdCI6MTY3OT...
```

---

## Parte 1: Endpoints Públicos

### 1. Cadastro de Novo Usuário

Cria um novo usuário com a role `USER`.

- **Endpoint**: `POST /usuarios`
- **Corpo da Requisição (`application/json`)**:
  ```json
  {
    "cpf": "11122233344",
    "nome": "Nome Completo do Usuário",
    "nickname": "apelido_unico",
    "email": "usuario@email.com",
    "telefone": "11999998888",
    "senha": "uma_senha_forte_123"
  }
  ```
- **Resposta de Sucesso (`201 CREATED`)**:
  ```json
  {
    "id": 1,
    "role": "USER",
    "cpf": "11122233344",
    "nome": "Nome Completo do Usuário",
    "nickname": "apelido_unico",
    "email": "usuario@email.com",
    "telefone": "11999998888"
  }
  ```

### 2. Login e Obtenção do Token

Autentica um usuário e retorna o token de acesso junto com os dados do usuário.

- **Endpoint**: `POST /auth/login`
- **Corpo da Requisição (`application/json`)**: O campo `identifier` pode ser o **CPF** ou o **e-mail**.
  ```json
  {
    "identifier": "usuario@email.com",
    "senha": "uma_senha_forte_123"
  }
  ```
- **Resposta de Sucesso (`200 OK`)**:
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvLnNpbHZhQGVtYWlsLmNvbSIsImlhdCI6MTY3OT...",
    "usuario": {
        "id": 1,
        "role": "USER",
        "cpf": "11122233344",
        "nome": "Nome Completo do Usuário",
        "nickname": "apelido_unico",
        "email": "usuario@email.com",
        "telefone": "11999998888"
    }
  }
  ```
  > **Nota para o Frontend**: Com esta resposta, o estado global do usuário (dados e token) pode ser populado com uma única chamada.

---

## Parte 2: Endpoints de Usuário (`USER` e `ADMIN`)

### 3. Gerenciamento de Perfil

Endpoints para visualizar e gerenciar os dados do próprio usuário.

- **Buscar Dados do Perfil**: `GET /usuarios/{cpf}`
- **Editar Dados do Perfil**: `PUT /usuarios/{cpf}`
- **Alterar a Senha**: `PUT /usuarios/{cpf}/senha`

---

## Parte 3: Fluxo de Denúncias (Role `USER`)

### 4. Criar uma Nova Denúncia com Anexo

Um usuário autenticado pode criar uma nova denúncia, opcionalmente enviando um arquivo (imagem).

- **Endpoint**: `POST /denuncias`
- **Content-Type**: `multipart/form-data`
- **Autorização**: `Bearer <token>`
- **Partes do Formulário**:
    1.  `denuncia`: Uma parte do tipo `application/json` contendo os dados da denúncia.
    2.  `anexo`: Uma parte do tipo `image/jpeg`, `image/png`, etc. (opcional).

- **Exemplo da parte `denuncia` (JSON)**:
  ```json
  {
    "titulo": "Lixo acumulado na praça central",
    "descricao": "Há várias semanas que o lixo não é coletado na praça em frente à prefeitura.",
    "endereco": {
      "cep": "12345678",
      "logradouro": "Praça da Sé",
      "bairro": "Centro",
      "cidade": "São Paulo",
      "estado": "SP"
    }
  }
  ```

- **Resposta de Sucesso (`201 CREATED`)**: Retorna a denúncia criada, incluindo os dados do anexo, se enviado.
  ```json
  {
    "id": 10,
    "titulo": "Lixo acumulado na praça central",
    "descricao": "Há várias semanas que o lixo não é coletado...",
    "dataCriacao": "2025-10-29T15:30:00Z",
    "status": "ABERTA",
    "usuario": { "id": 1, "nome": "Nome do Usuário", "nickname": "nick_usuario" },
    "endereco": { "cep": "12345678", ... },
    "respostas": [],
    "anexo": {
        "id": 1,
        "nomeOriginal": "foto-lixo.jpg",
        "urlAcesso": "/api/files/denuncias/usuario_1/denuncia_10_1668525300000.jpg",
        "contentType": "image/jpeg",
        "tamanhoBytes": 512000
    }
  }
  ```

### 5. Listar Minhas Denúncias

Retorna a lista de denúncias feitas pelo usuário autenticado.

- **Endpoint**: `GET /denuncias/minhas-denuncias`
- **Autorização**: `Bearer <token>`
- **Resposta de Sucesso (`200 OK`)**: Uma lista de objetos de denúncia.

### 6. Editar Denúncia

Permite que o usuário edite o título e a descrição de uma denúncia que ele criou, **desde que o status seja `ABERTA`**.

- **Endpoint**: `PUT /denuncias/{id}`
- **Autorização**: `Bearer <token>`

---

## Parte 4: Fluxo de Administração (Role `ADMIN`)

### 7. Listar Todas as Denúncias

Um administrador pode listar todas as denúncias cadastradas na plataforma.

- **Endpoint**: `GET /denuncias`
- **Autorização**: `Bearer <admin_token>`
- **Resposta de Sucesso (`200 OK`)**: Retorna uma lista de denúncias. O objeto de cada denúncia é completo, incluindo dados do usuário, endereço, respostas e anexo.

### 8. Buscar Denúncia Específica

Busca uma denúncia pelo seu ID, retornando todos os seus detalhes.

- **Endpoint**: `GET /denuncias/buscar/{id}`
- **Autorização**: `Bearer <admin_token>`
- **Resposta de Sucesso (`200 OK`)**: Retorna um único objeto de denúncia completo.

### 9. Visualizar Anexos

O objeto `anexo` dentro de uma denúncia contém um campo `urlAcesso`. Para visualizar o arquivo, o frontend deve montar a URL completa.

- **URL do Anexo**: `URL_BASE_DA_API` + `anexo.urlAcesso`
- **Exemplo**: `http://localhost:8080/api/files/denuncias/usuario_1/denuncia_10_1668525300000.jpg`

Esta URL pode ser usada no atributo `src` de uma tag `<img>` para imagens ou em um link `<a>` para outros tipos de arquivo. O endpoint requer autenticação, então o frontend deve garantir que o token seja enviado (geralmente o navegador faz isso automaticamente via cookies ou se a imagem for carregada via JavaScript com o header `Authorization`).

### 10. Atualizar Status de uma Denúncia

Altera o status de uma denúncia (ex: de `ABERTA` para `EM_ANALISE`).

- **Endpoint**: `PATCH /denuncias/{id}/status`
- **Autorização**: `Bearer <admin_token>`
- **Corpo da Requisição (`application/json`)**:
  ```json
  {
    "status": "EM_ANALISE"
  }
  ```

### 11. Criar uma Resposta para uma Denúncia

Adiciona uma resposta a uma denúncia existente.

- **Endpoint**: `POST /respostas`
- **Autorização**: `Bearer <admin_token>`
- **Corpo da Requisição (`application/json`)**:
  ```json
  {
    "mensagem": "Sua denúncia foi recebida e uma equipe será enviada ao local em 48 horas.",
    "denunciaId": 10
  }
  ```

### 12. Obter Estatísticas do Dashboard

Retorna dados consolidados para o dashboard do administrador.

- **Endpoint**: `GET /admin/dashboard/stats`
- **Autorização**: `Bearer <admin_token>`
- **Resposta de Sucesso (`200 OK`)**:
  ```json
  {
    "totalUsuarios": 150,
    "totalDenuncias": 89,
    "denunciasAbertas": 23,
    "denunciasEmAnalise": 18,
    "denunciasFechadas": 48
  }
  ```

---

## Como Testar no Swagger UI

1.  Execute `POST /auth/login` com credenciais válidas.
2.  Copie o `token` da resposta.
3.  Clique no botão **`Authorize`** no topo da página.
4.  Na janela, cole o token no campo "Value", prefixado com `Bearer ` (com espaço). Ex: `Bearer eyJ...`
5.  Clique em "Authorize" e feche a janela.
6.  Agora você pode executar qualquer endpoint protegido.
> **Nota para `POST /denuncias`**: Testar endpoints `multipart/form-data` no Swagger pode ser confuso. Você precisará enviar a parte `denuncia` como uma string JSON e fazer o upload do arquivo na parte `anexo`.