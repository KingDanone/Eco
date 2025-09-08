# Fluxo da Aplicação Eco

Este documento descreve o fluxo principal da aplicação, detalhando os passos desde a criação de um usuário até a resposta de uma denúncia.

## Fluxo Principal Revisado

O fluxo principal da aplicação foi otimizado para ser mais direto e centrado na entidade `Denuncia`.

1.  **Criação de Usuário:** Um novo usuário (comum ou admin) é criado no sistema.
2.  **Criação de Denúncia:** Um usuário comum cria uma nova denúncia. O status da denúncia é automaticamente definido como `ABERTA`.
3.  **Consulta de Denúncias (Admin):** Um usuário administrador consulta as denúncias, podendo filtrar por status (`ABERTA`, `EM_ANALISE`, `FECHADA`).
4.  **Resposta à Denúncia (Admin):** O administrador responde diretamente a uma denúncia. Ao fazer isso, o status da denúncia é automaticamente alterado para `EM_ANALISE`.
5.  **(Futuro) Fechamento de Denúncia (Admin):** O administrador poderá, futuramente, marcar uma denúncia como `FECHADA`.

## Detalhes do Fluxo e Status de Implementação

### 1. Criação de Usuário

*   **Endpoint:** `POST /usuarios`
*   **Status:** ✅ **Funcional**
*   **Observações:** Novos usuários são criados com sucesso. Para testes, um `data.sql` foi adicionado para criar um usuário `ADMIN` e um `COMUM` na inicialização.

### 2. Criação de Denúncia

*   **Endpoint:** `POST /denuncias`
*   **Status:** ✅ **Funcional**
*   **Observações:** Um usuário pode criar uma denúncia. A denúncia é criada com o status `ABERTA` por padrão.

### 3. Consulta de Denúncias (Admin)

*   **Endpoint:** `GET /atendimentos/denuncias`
*   **Parâmetro:** `?status={ABERTA | EM_ANALISE | FECHADA}` (opcional)
*   **Status:** ✅ **Funcional**
*   **Observações:** Endpoint implementado para que administradores possam listar denúncias e filtrar por status.

### 4. Resposta à Denúncia (Admin)

*   **Endpoint:** `POST /respostas`
*   **DTO de Requisição:** `RespostaDTO` agora usa `denunciaId`.
*   **Status:** ✅ **Funcional**
*   **Observações:**
    *   Um administrador pode responder diretamente a uma denúncia.
    *   Ao criar uma `Resposta`, o status da `Denuncia` associada é automaticamente alterado para `EM_ANALISE`.

## Funcionalidades a Implementar

*   **Endpoint para fechar denúncia:** Criar um endpoint para o admin alterar o status de uma denúncia para `FECHADA`.
*   **Spring Security:**
    *   Implementar controle de acesso baseado em papéis (roles) para diferenciar usuários comuns de administradores.
    *   Proteger os endpoints de acordo com os papéis dos usuários.
*   **Endpoint secreto para criação de admins:**
    *   Criar um endpoint protegido para a criação de usuários administradores (alternativa ao `data.sql`).
*   **Refatoração de DTOs:** Continuar a otimização dos DTOs para usar IDs em vez de objetos aninhados, conforme a necessidade.
