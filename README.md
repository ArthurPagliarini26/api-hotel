# API Hotel

API REST desenvolvida em Java com Spring Boot para realizar o cadastro e o gerenciamento básico de clientes de um hotel. O projeto demonstra a organização em camadas, o uso de DTOs, validação de entrada e operações HTTP para consultar e atualizar clientes.

## Tecnologias utilizadas

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Jakarta Bean Validation
- Lombok
- Springdoc OpenAPI / Swagger UI
- Maven

## Funcionalidades

- Cadastrar um cliente.
- Listar todos os clientes cadastrados.
- Buscar um cliente pelo ID.
- Atualizar os dados de um cliente.
- Atualizar parcialmente os dados de um cliente.
- Validar campos obrigatórios na criação e na atualização completa.

> **Importante:** nesta versão, os clientes são armazenados em uma lista na memória da aplicação. Não há banco de dados configurado. Os dados são perdidos quando a aplicação é encerrada ou reiniciada.

## Requisitos

Antes de executar o projeto, tenha instalado:

- JDK 21 ou compatível com a versão configurada no `pom.xml`.
- Git, caso deseje clonar o repositório.
- Uma IDE Java (IntelliJ IDEA, Eclipse ou VS Code) ou terminal.

O projeto inclui Maven Wrapper, portanto não é necessário instalar o Maven separadamente.

## Como executar

1. Clone o repositório ou extraia os arquivos do projeto.
2. Entre na pasta que contém o arquivo `pom.xml`.
3. Execute a aplicação.

### Windows (PowerShell ou Prompt de Comando)

```bash
.\mvnw.cmd spring-boot:run
```

### Linux ou macOS

```bash
./mvnw spring-boot:run
```

Também é possível abrir o projeto na IDE e executar a classe `ApiHotelApplication`.

A aplicação está configurada para iniciar na porta **8081**.

URL base da API:

```text
http://localhost:8081
```

## Rotas da API

Todas as rotas de clientes usam o prefixo `/api-hotel/cliente`.

| Método HTTP | Rota | Descrição | Resposta esperada |
|---|---|---|---|
| `POST` | `/api-hotel/cliente` | Cadastra um cliente | `201 Created` |
| `GET` | `/api-hotel/cliente` | Lista os clientes cadastrados | `200 OK` |
| `GET` | `/api-hotel/cliente/{id}` | Busca um cliente pelo ID | `200 OK` ou `404 Not Found` |
| `PUT` | `/api-hotel/cliente/{id}` | Atualiza o nome e o telefone do cliente | `200 OK` ou `404 Not Found` |
| `PATCH` | `/api-hotel/cliente/{id}` | Atualiza parcialmente o nome e/ou telefone | `200 OK` ou `404 Not Found` |

### 1. Cadastrar cliente

**Requisição**

```http
POST /api-hotel/cliente
Content-Type: application/json
```

**Corpo JSON**

```json
{
  "nome": "João da Silva",
  "telefone": "47999999999",
  "cpf": "12345678900"
}
```

Os campos `nome`, `telefone` e `cpf` são obrigatórios e não podem ser vazios.

**Exemplo de resposta — `201 Created`**

```json
{
  "id": 1,
  "nome": "João da Silva",
  "telefone": "47999999999",
  "cpf": "12345678900"
}
```

O ID é gerado automaticamente pela aplicação.

### 2. Listar clientes

**Requisição**

```http
GET /api-hotel/cliente
```

**Exemplo de resposta — `200 OK`**

```json
[
  {
    "id": 1,
    "nome": "João da Silva",
    "telefone": "47999999999",
    "cpf": "12345678900"
  },
  {
    "id": 2,
    "nome": "Maria Souza",
    "telefone": "47988888888",
    "cpf": "98765432100"
  }
]
```

Caso não existam clientes cadastrados, a API retorna uma lista vazia:

```json
[]
```

### 3. Buscar cliente por ID

**Requisição**

```http
GET /api-hotel/cliente/1
```

**Exemplo de resposta — `200 OK`**

```json
{
  "id": 1,
  "nome": "João da Silva",
  "telefone": "47999999999",
  "cpf": "12345678900"
}
```

Se o ID não existir, a API retorna `404 Not Found`.

### 4. Atualizar cliente (PUT)

O `PUT` atualiza os campos previstos pelo endpoint: `nome` e `telefone`. O CPF não é alterado por esta operação.

**Requisição**

```http
PUT /api-hotel/cliente/1
Content-Type: application/json
```

**Corpo JSON**

```json
{
  "nome": "João da Silva Atualizado",
  "telefone": "47997776666"
}
```

Os campos `nome` e `telefone` são obrigatórios e não podem ser vazios.

**Exemplo de resposta — `200 OK`**

```json
{
  "id": 1,
  "nome": "João da Silva Atualizado",
  "telefone": "47997776666",
  "cpf": "12345678900"
}
```

Se o cliente não existir, a API retorna `404 Not Found`.

### 5. Atualizar parcialmente cliente (PATCH)

O `PATCH` permite enviar apenas os campos que devem ser alterados. Atualmente, os campos aceitos são `nome` e `telefone`.

**Requisição**

```http
PATCH /api-hotel/cliente/1
Content-Type: application/json
```

**Exemplo: atualizar somente o telefone**

```json
{
  "telefone": "47996665555"
}
```

**Exemplo: atualizar somente o nome**

```json
{
  "nome": "João Silva"
}
```

**Exemplo de resposta — `200 OK`**

```json
{
  "id": 1,
  "nome": "João da Silva Atualizado",
  "telefone": "47996665555",
  "cpf": "12345678900"
}
```

Os campos omitidos ou enviados como `null` não são modificados. Se o cliente não existir, a API retorna `404 Not Found`.

## Documentação interativa (Swagger)

O projeto inclui a dependência Springdoc OpenAPI. Com a aplicação em execução, tente acessar a interface Swagger UI em:

```text
http://localhost:8081/swagger-ui/index.html
```

A documentação OpenAPI em formato JSON normalmente fica disponível em:

```text
http://localhost:8081/v3/api-docs
```

## Organização do projeto

Os arquivos principais estão no pacote `com.estudo.api_hotel`.

```text
src/
└── main/
    ├── java/com/estudo/api_hotel/
    │   ├── ApiHotelApplication.java
    │   ├── controller/
    │   │   └── ClienteController.java
    │   ├── dto/
    │   │   ├── patchs/
    │   │   │   └── ClientePatch.java
    │   │   ├── requests/
    │   │   │   └── ClienteRequest.java
    │   │   ├── responses/
    │   │   │   └── ClienteResponse.java
    │   │   └── updates/
    │   │       └── ClienteUpdate.java
    │   ├── entity/
    │   │   └── Cliente.java
    │   ├── mapper/
    │   │   └── ClienteMapper.java
    │   ├── repositoy/
    │   │   └── ClienteRepository.java
    │   └── service/
    │       └── ClienteService.java
    └── resources/
        └── application.properties
```

### Responsabilidade de cada camada

- **Controller (`ClienteController`):** recebe as requisições HTTP, chama o serviço e define os códigos de resposta.
- **Service (`ClienteService`):** concentra as operações e regras de gerenciamento de clientes.
- **Repository (`ClienteRepository`):** mantém a lista em memória, gera IDs e localiza clientes.
- **Entity (`Cliente`):** representa os dados de um cliente.
- **DTOs (`dto`):** definem os dados recebidos e enviados pela API:
    - `ClienteRequest`: dados recebidos no cadastro.
    - `ClienteResponse`: dados retornados nas respostas.
    - `ClienteUpdate`: campos exigidos na atualização via `PUT`.
    - `ClientePatch`: campos opcionais na atualização parcial via `PATCH`.
- **Mapper (`ClienteMapper`):** converte objetos entre DTOs e a entidade `Cliente`.
- **Classe principal (`ApiHotelApplication`):** inicia a aplicação Spring Boot.

## Validação

A API utiliza Jakarta Bean Validation. No cadastro, `nome`, `telefone` e `cpf` possuem a anotação `@NotBlank`. Na atualização via `PUT`, `nome` e `telefone` também são obrigatórios.

Quando os dados não atendem às validações, o Spring retorna uma resposta de erro de validação. O formato exato do corpo de erro depende da configuração e da versão do Spring Boot.

A validação atual verifica se os campos obrigatórios não estão vazios. Ela não confirma, por si só, se o CPF é válido ou se o telefone possui um formato específico.

## Testando com Postman ou Insomnia

1. Inicie a aplicação.
2. Crie uma requisição para `http://localhost:8081/api-hotel/cliente`.
3. Selecione o método HTTP desejado.
4. Para `POST`, `PUT` e `PATCH`, escolha o corpo no formato JSON e informe `Content-Type: application/json`.
5. Execute primeiro um `POST` para cadastrar um cliente.
6. Use o ID retornado para testar `GET`, `PUT` e `PATCH`.

## Limitações conhecidas

- Os dados são armazenados somente em memória; não há persistência em banco de dados.
- A API ainda não possui rota para excluir clientes (`DELETE`).
- Não há autenticação ou autorização implementadas.
- O `PATCH` permite alterar apenas `nome` e `telefone`.
- O `PUT` também altera apenas `nome` e `telefone`; o CPF permanece igual.
- Não há tratamento personalizado de erros documentado para todos os cenários.

## Possíveis melhorias futuras

- Integrar um banco de dados, como MySQL ou PostgreSQL.
- Implementar exclusão de clientes.
- Adicionar validação de CPF e de telefone.
- Criar tratamento padronizado de erros.
- Adicionar testes automatizados para os endpoints.
- Implementar autenticação e autorização, se necessário.

## Testes

Para executar os testes automatizados:

### Windows

```bash
.\mvnw.cmd test
```

### Linux ou macOS

```bash
./mvnw test
```

## Licença

Nenhuma licença específica foi identificada nos arquivos analisados. Se o projeto for publicado, defina uma licença conforme a finalidade e as permissões desejadas.
