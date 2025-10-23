# Fluxo de Uso da API Eco

Este documento descreve o fluxo de autenticação e os principais casos de uso da API, servindo como um guia para desenvolvedores frontend e para testes.

---

## Autenticação

A API utiliza **JSON Web Tokens (JWT)** para gerenciar a autenticação. Quase todos os endpoints são protegidos e exigem um token válido.

O fluxo geral é:
1.  O usuário se cadastra ou faz login através de um endpoint público.
2.  A API retorna um token JWT e os dados do usuário.
3.  Para todas as requisições subsequentes a endpoints protegidos, o frontend deve enviar este token no cabeçalho `Authorization`.

### Formato do Cabeçalho de Autenticação

- **Chave**: `Authorization`
- **Valor**: `Bearer <seu_token_jwt>`

**Exemplo:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvLnNpbHZhQGVtYWlsLmNvbSIsImlhdCI6MTY3OT...
```

---

## Parte 1: Endpoints Públicos (Não precisam de Token)

### 1. Cadastro de Novo Usuário

Cria um novo usuário comum na plataforma.

- **Endpoint**: `POST /usuarios`
- **Método**: `POST`
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
- **Resposta de Sucesso (`201 CREATED`)**: Retorna o objeto do usuário criado. Note a inclusão do campo `role`.
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

Autentica um usuário existente e gera o token de acesso junto com os dados do usuário, otimizando o fluxo de login.

- **Endpoint**: `POST /auth/login`
- **Método**: `POST`
- **Corpo da Requisição (`application/json`)**: O campo `identifier` pode ser o CPF ou o e-mail do usuário.
  ```json
  {
    "identifier": "usuario@email.com",
    "senha": "uma_senha_forte_123"
  }
  ```
- **Resposta de Sucesso (`200 OK`)**: Retorna um objeto contendo o token JWT e os dados completos do usuário.
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
  > **Nota para o Frontend**: Com esta resposta, o estado global do usuário (dados e token) pode ser populado com uma única chamada, simplificando a lógica de login.

---

## Parte 2: Endpoints Protegidos (Precisa de Token)

Para todos os endpoints a seguir, o cabeçalho `Authorization: Bearer <token>` é **obrigatório**.

### 3. Buscar Dados do Perfil

Retorna os dados de um usuário específico, incluindo sua `role`.

- **Endpoint**: `GET /usuarios/{cpf}`
- **Método**: `GET`
- **Parâmetro de URL**:
  - `cpf`: O CPF do usuário a ser consultado.
- **Resposta de Sucesso (`200 OK`)**: Retorna o objeto do usuário. O frontend pode usar o campo `role` para controlar o acesso a rotas e a exibição de componentes.
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

### 4. Editar Dados do Perfil

Atualiza informações de um usuário (nome, nickname, email, telefone). **Este endpoint não altera a senha.**

- **Endpoint**: `PUT /usuarios/{cpf}`
- **Método**: `PUT`
- **Parâmetro de URL**:
  - `cpf`: O CPF do usuário a ser editado.
- **Corpo da Requisição (`application/json`)**: Incluir apenas os campos a serem alterados.
  ```json
  {
    "nome": "Novo Nome do Usuário",
    "telefone": "11911112222"
  }
  ```
- **Resposta de Sucesso (`200 OK`)**: Retorna o objeto do usuário com os dados atualizados.

### 5. Alterar a Senha (Forma Segura)

Altera a senha do usuário, exigindo a senha atual como medida de segurança.

- **Endpoint**: `PUT /usuarios/{cpf}/senha`
- **Método**: `PUT`
- **Parâmetro de URL**:
  - `cpf`: O CPF do usuário.
- **Corpo da Requisição (`application/json`)**:
  ```json
  {
    "currentPassword": "a_senha_antiga_e_correta",
    "newPassword": "uma_nova_senha_mais_forte_456"
  }
  ```
- **Resposta de Sucesso (`204 No Content`)**: Resposta sem corpo, indicando que a senha foi alterada.

---

## Como Testar no Swagger UI

1.  Execute `POST /auth/login` com credenciais válidas.
2.  Copie o `token` da resposta.
3.  Clique no botão **`Authorize`** no topo da página.
4.  Na janela, cole o token no campo "Value", prefixado com `Bearer ` (com espaço). Ex: `Bearer eyJ...`
5.  Clique em "Authorize" e feche a janela.
6.  Agora você pode executar qualquer endpoint protegido diretamente pela interface do Swagger.

---

## Parte 3: Fluxo Principal da Aplicação

### 6. Criar uma Nova Denúncia (Requer Role 'USER')

Um usuário autenticado com a role `USER` pode criar uma nova denúncia.

- **Endpoint**: `POST /denuncias`
- **Método**: `POST`
- **Autorização**: `Bearer <token>`
- **Corpo da Requisição (`application/json`)**:
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
- **Resposta de Sucesso (`201 CREATED`)**: Retorna o objeto da denúncia criada, com o ID do usuário e o status inicial preenchidos pelo backend.
  ```json
  {
    "id": 10,
    "titulo": "Lixo acumulado na praça central",
    "descricao": "Há várias semanas que o lixo não é coletado na praça em frente à prefeitura.",
    "dataCriacao": "2025-09-11T15:30:00Z",
    "status": "ABERTA",
    "usuario": {
      "id": 1,
      "nome": "Nome Completo do Usuário"
    },
    "endereco": {
      "cep": "12345678",
      "logradouro": "Praça da Sé",
      "bairro": "Centro",
      "cidade": "São Paulo",
      "estado": "SP"
    }
  }
  ```

### 7. Listar Todas as Denúncias (Requer Role 'ADMIN')

Um administrador pode listar todas as denúncias cadastradas.

- **Endpoint**: `GET /denuncias`
- **Método**: `GET`
- **Autorização**: `Bearer <token>`
- **Resposta de Sucesso (`200 OK`)**: Retorna uma lista de denúncias.

### 8. Atualizar Status de uma Denúncia (Requer Role 'ADMIN')

Um administrador pode alterar o status de uma denúncia (ex: de 'ABERTA' para 'EM_ANALISE').

- **Endpoint**: `PATCH /denuncias/{id}/status`
- **Método**: `PATCH`
- **Autorização**: `Bearer <token>`
- **Parâmetro de URL**:
  - `id`: O ID da denúncia a ser atualizada.
- **Corpo da Requisição (`application/json`)**:
  ```json
  {
    "status": "EM_ANALISE"
  }
  ```
- **Resposta de Sucesso (`200 OK`)**: Retorna o objeto da denúncia com o status atualizado.

### 9. Criar uma Resposta para uma Denúncia (Requer Role 'ADMIN')

Um administrador pode adicionar uma resposta a uma denúncia existente.

- **Endpoint**: `POST /respostas`
- **Método**: `POST`
- **Autorização**: `Bearer <token>`
- **Corpo da Requisição (`application/json`)**:
  ```json
  {
    "mensagem": "Sua denúncia foi recebida e uma equipe será enviada ao local em 48 horas.",
    "denunciaId": 10
  }
  ```
- **Resposta de Sucesso (`200 OK`)**: Retorna o objeto da resposta criada.
