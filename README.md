**Especificação de Requisitos - API “Pedra, Papel ou Tesoura”**

1. **Objetivo**

Desenvolver uma API REST em Java (versão 21) utilizando Spring Boot e PostgreSQL como banco de dados, que simule várias rodadas do jogo “Pedra, Papel ou Tesoura” entre dois jogadores. A aplicação deve seguir os princípios de Orientação a Objetos (OOP) e Clean Code, com uma separação clara de responsabilidades em classes.![](Aspose.Words.450a32ab-a2b0-4438-9f51-6cafec678525.001.png)

2. **Escopo e Funcionalidades**

**2.1 Funcionalidades Básicas (Requisitos Obrigatórios)**

1. **Simulação de Rodadas**
- **Descrição:**

  A API deve simular uma rodada do jogo, recebendo os movimentos de dois jogadores e retornando o resultado da rodada.

- **Endpoint:**

  POST /api/game/play

- **Entrada (JSON):**
- playerOneMove: String representando o movimento do Jogador 1 (valores permitidos: "ROCK", "PAPER", "SCISSORS").
- playerOneName: String representando o nome do Jogador 2 (não pode ser nula/vazia).
- playerTwoMove: String representando o movimento do Jogador 2 (valores permitidos: "ROCK", "PAPER", "SCISSORS").
- playerTwoName: String representando o nome do Jogador 2 (não pode ser nula/vazia).
- **Entidade GameHistory (Banco de Dados):**
- id - “Primary key, auto gerada”
- playerOneMove - “String, não nula/vazia)”
- playerOneName - “String, não nula/vazia)”
- playerTwoMove - “String, não nula/vazia)”
- playerTwoName - “String, não nula/vazia)”
- date - “LocalDateTime, não nulo/vazio”
- **Processamento:**
- **Validação:**

  Verificar se os inputs na payload json correspondem aos valores válidos, considerando case-insensitive. E caso não forem, seguir a regra de tratamento de erros e validações citada posteriormente.

- **Lógica de Jogo:**
- Se os movimentos forem iguais: resultado = DRAW.
- Caso contrário, aplicar as regras:
  - Pedra vence Tesoura.
  - Tesoura vence Papel.
  - Papel vence Pedra.
- **Registro no Histórico:**

  **Persistir** no banco todas as rodadas realizadas junto com a data e horário que aconteceu.

- **Saída (JSON):**
- Movimento do Jogador 1.
- Nome do Jogador 1.
- Movimento do Jogador 2.
- Nome do Jogador 2.
- Resultado da rodada (PLAYER\_ONE\_WINS, PLAYER\_TWO\_WINS, DRAW).
2. **Histórico de Rodadas**
- **Descrição:**

  Disponibilizar um endpoint para consulta do histórico das rodadas.

- **Validação:**

· Caso na o tenha nenhuma rodada no banco, retornar uma lista de 

rodadas vazia.

- **Endpoint:**

  GET /api/game/history

- **Saída (JSON):**

  Uma lista contendo, para cada rodada:

- Movimento do Jogador 1.
- Nome do Jogador 1.
- Movimento do Jogador 2.
- Nome do Jogador 2.
- Resultado da rodada.
- Data e horário da rodada
3. **Tratamento de Erros e Validações**
- **Entradas Inválidas:**

  Se os inputs não forem um dos valores válidos, a API deverá:

- Retornar status HTTP 400 (Bad Request).
- Fornecer uma mensagem de erro detalhada.

**3 Requisitos técnicos**

- **Tecnologias:**
- Backend: **Java com Spring Boot** (última versão estável)
- Banco de Dados: **PostgreSQL**
- Persistência: **Spring Data JPA**
- Build: **Gradle**
- Versionamento: **Git**

**4 Configuração do Banco de Dados:**

- **PostgreSQL**
- Configurar o acesso ao PostgreSQL via application.yml.
- **Opcional**: Utilizar Docker para orquestrar o container do PostgreSQL e facilitar o setup do ambiente de desenvolvimento.

**5 Orientação a Objetos e Clean Code**

- As classes devem seguir obrigatoriamente e de forma clara os princípios de orientação a objetos e boas práticas de clean code.

**6 Controle de Versão e Fluxo de Trabalho**

- O projeto deve usar o repositório disponibilizado via Teams.
- **Branching:**
- Criação de uma branch separada para o desenvolvimento do desafio.
- **Commits:**
- Realizar commits descritivos.
- **Pull Request:**
- Ao finalizar, abrir um Pull Request para a branch main e aguardar revisão antes do merge.

**7 Desafios não obrigatórios:**

**7.1 Auditoria de Operações com Log4j (Opcional)**

- **Objetivo:** Implementar uma camada de auditoria utilizando o logger do Log4j para registrar as operações de CRUD realizadas na aplicação.
- **Requisitos:**
- **Configuração do Logger:** : Instale a dependência do Log4j no projeto e utilize o logger para auditar informações.
- **Registro de Eventos:**
- Registrar logs informativos (INFO) ao finalizar uma rodada
- Incluir detalhes como o ID da rodada criada, timestamp da operação e, informações sobre o resultado da rodada.
- Registrar logs de erro quando alguma validação falhar
- Incluir detalhes como a mensagem do erro e o request que foi tentado.
- **Formatos e Níveis:**
- Utilize níveis de log apropriados para cada tipo de evento (INFO para operações normais, ERROR para exceções, etc.).

**7.1.2 Testes unitários da lógica (Opcional)**

- **Objetivo:** Objetivo: Implementar testes unitários que busquem cubrir o coverage da parte que envolve a lógica do jogo.
