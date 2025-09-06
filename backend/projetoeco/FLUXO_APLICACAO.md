# Fluxo da Aplicação Eco

Este documento descreve o fluxo principal da aplicação, detalhando os passos desde a criação de um usuário até a resposta de uma denúncia.

## Fluxo Principal

O fluxo principal da aplicação consiste nos seguintes passos:

1.  **Criação de Usuário:** Um novo usuário (comum) é criado no sistema.
2.  **Criação de Denúncia:** O usuário cria uma nova denúncia, fornecendo os detalhes da denúncia e o endereço da ocorrência.
3.  **Listagem de Denúncias (Admin):** Um usuário administrador lista todas as denúncias para análise.
4.  **Criação de Atendimento (Admin):** O administrador cria um atendimento para uma denúncia específica.
5.  **Resposta à Denúncia (Admin):** O administrador responde à denúncia através do atendimento.

## Detalhes do Fluxo e Status de Implementação

### 1. Criação de Usuário

*   **Endpoint:** `POST /usuarios`
*   **Controller:** `UsuarioController.criar()`
*   **Service:** `UsuarioServiceImpl.criar()`
*   **Status:** ✅ **Funcional**
*   **Observações:** Um novo usuário é criado com sucesso.

### 2. Criação de Denúncia

*   **Endpoint:** `POST /denuncias`
*   **Controller:** `DenunciaController.criar()`
*   **Service:** `DenunciaServiceImpl.criarDenuncia()`
*   **Status:** ✅ **Funcional**
*   **Observações:** Um usuário pode criar uma denúncia e associar um endereço a ela.

### 3. Listagem de Denúncias (Admin)

*   **Endpoint:** `GET /denuncias`
*   **Controller:** `DenunciaController.listar()`
*   **Service:** `DenunciaServiceImpl.listarTodas()`
*   **Status:** ✅ **Funcional**
*   **Observações:** Todas as denúncias são listadas com sucesso.

### 4. Criação de Atendimento (Admin)

*   **Endpoint:** `POST /atendimentos`
*   **Controller:** `AtendimentoController.criar()`
*   **Service:** `AtendimentoServiceImpl.criar()`
*   **Status:** ✅ **Funcional, mas com melhorias pendentes.**
*   **Observações:**
    *   Atualmente, um administrador precisa criar manually um atendimento para cada denúncia.
    *   **Melhoria pendente:** A criação de um `Atendimento` deveria ser automática quando uma `Denúncia` é criada. O status inicial do `Atendimento` deveria ser `ABERTO`.

### 5. Resposta à Denúncia (Admin)

*   **Endpoint:** `POST /respostas`
*   **Controller:** `RespostaController.criar()`
*   **Service:** `RespostaServiceImpl.criarResposta()`
*   **Status:** ✅ **Funcional, mas com melhorias pendentes.**
*   **Observações:**
    *   Um administrador pode responder a um atendimento.
    *   **Melhoria pendente:** Ao responder a um atendimento, o status do `Atendimento` deveria ser alterado para `CONCLUIDO`.

## Funcionalidades a Implementar

*   **Atualização automática de status:**
    *   Ao criar um `Atendimento`, o status deve ser definido como `EM_ANALISE` automaticamente.
    *   Ao criar uma `Resposta`, o status do `Atendimento` associado deve ser alterado para `CONCLUIDO`.
*   **Spring Security:**
    *   Implementar controle de acesso baseado em papéis (roles) para diferenciar usuários comuns de administradores.
    *   Proteger os endpoints de acordo com os papéis dos usuários.
*   **Endpoint secreto para criação de admins:**
    *   Criar um endpoint protegido para a criação de usuários administradores.
