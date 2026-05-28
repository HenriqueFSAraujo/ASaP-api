# CLAUDE.md — Contexto do projeto `agenda-service`

Este arquivo orienta agentes (Claude Code e similares) em sessões futuras. Antes de propor mudanças, leia esta seção inteira.

> **Projeto irmão (front-end)**: `C:\workspace-henrique\MSaT_APP` (`edu-bolsas-frontend`) — React 18 + Vite + TypeScript.
> **Mudanças que afetam contrato de API (request/response) devem ser coordenadas** com o front. Ver `CLAUDE.md` lá.

---

## 1. Visão geral

**Domínio**: Cadastro Social de Bolsas — sistema para coleta e análise socioeconômica de candidatos a bolsas de estudo. O fluxo principal é:

1. Admin cria um usuário (`UserInfo`)
2. Usuário faz login e preenche múltiplos formulários (dados pessoais, parentes, endereço, condições habitacionais, bens, despesas, composição familiar, documentos PDF, declarações)
3. Admin avalia e gera um **Parecer Socioeconômico** (em HTML/PDF) com base nos dados

O nome `agenda-service` é histórico — o domínio "Agenda/Paciente" existe no código, mas o sistema evoluiu para cadastro de bolsas (ver `description` no `pom.xml`).

**Front-end consumidor**: `https://educabolsas.netlify.app` (CORS configurado em `SecurityConfig`).
**Deploy de produção observado**: `https://asap-api-production.up.railway.app` (Railway).

---

## 2. Stack

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 17 (Dockerfile usa Amazon Corretto 17) |
| Build | Maven 3.x (`./mvnw`) |
| Framework | **Spring Boot 2.5.9** (fora de suporte oficial) |
| Persistência | Spring Data JPA + Hibernate + PostgreSQL |
| Migrations | Flyway (51 migrations — ver `src/main/resources/db/migration/`) |
| Segurança | Spring Security + JWT (`com.auth0:java-jwt:3.18.3`) |
| Mapeamento DTO | **MapStruct 1.5.5** + **ModelMapper 2.4.2** (redundante — escolher um) |
| API Docs | **Springfox 3.0.0** (deprecated) + algumas anotações `io.swagger.v3` (Springdoc) misturadas |
| HTTP client | Spring Cloud OpenFeign 3.0.7 |
| PDF | `com.github.librepdf:openpdf:1.3.30` |
| Banco em testes | H2 (escopo `test`) |
| Container | Docker (`Dockerfile` + `docker-compose.yml` com Postgres + pgAdmin) |

> `system.properties` declara `java.runtime.version=11` — divergente do `pom.xml` (17). Provavelmente legado de deploy Heroku. Verificar antes de mexer.

---

## 3. Estrutura de pacotes (estado atual)

> A estrutura está **inconsistente**: existem pacotes paralelos na raiz E dentro de `domain/`. Funcionalidades antigas estão em `domain/...`, e duas funcionalidades novas (Composição Familiar, Declaração) foram colocadas em `controller/`, `service/`, etc. na raiz.

```
src/main/java/pdev/com/agenda/
├── AgendaApplication.java              ← Bootstrap
├── config/                             ← Security, JWT, ModelMapper, Swagger
│   ├── SecurityConfig.java
│   ├── CustomAuthenticationFilterConfig.java
│   ├── CustomAuthorizationFilterConfig.java
│   ├── TokenService.java               ← Geração/validação de JWT
│   ├── PasswordEncoderConfig.java      ← BCrypt declarado (mas NÃO usado, ver §6)
│   ├── ModalMapperConfig.java          ← typo: "Modal" → "Model"
│   └── SwaggerConfig.java              ← Limita scan a domain.controller (ver §6)
│
├── controller/                         ← (paralelo a domain/controller)
│   ├── ComposicaoFamiliarController.java
│   └── DeclaracaoController.java
├── service/                            ← (paralelo a domain/service)
├── repository/                         ← (paralelo a domain/repository)
├── dto/                                ← (paralelo a domain/dto)
├── mapper/                             ← (paralelo a domain/mapper)
│
├── domain/
│   ├── controller/                     ← Maioria dos controllers
│   ├── service/
│   ├── repository/
│   ├── entity/                         ← Todas as entidades JPA
│   ├── dto/
│   ├── mapper/                         ← MapStruct mappers
│   ├── enuns/                          ← (sic — "enuns" em vez de "enums")
│   └── UserInfoResponse.java           ← solto, fora de subpacote
│
├── exception/
│   └── BusinessException.java          ← Único; SEM GlobalExceptionHandler
│
└── util/
    ├── FlywayRepairRunner.java         ← @PostConstruct executa flyway.repair() (anti-pattern)
    ├── PdfUtil.java
    └── ValidationUtil.java
```

**Pacote base**: `pdev.com.agenda` — segue convenção invertida (o padrão Java seria `com.pdev.agenda`). Não corrigir sem coordenar refactor amplo.

---

## 4. Mapa de endpoints (estado atual)

> **Atenção**: `SecurityConfig.AUTH_WHITELIST` inclui `/api/**` — todos os endpoints abaixo estão **publicamente acessíveis sem JWT**. Ver §6.

| Método | Path | Controller | Função |
|---|---|---|---|
| POST | `/api/auth/login` | `AuthController` | Login, retorna JWT |
| GET/POST/PUT/DELETE | `/api/users` | `UserInfoController` | CRUD de usuários. **POST exige `tipoAluno` quando `roleName=ROLE_USER`** (ver §11) |
| PUT | `/api/users/reset-password/{id}` | `UserInfoController` | Trocar senha |
| PUT | `/api/users/status/{id}` | `UserInfoController` | Ativar/desativar usuário |
| GET/POST | `/api/forms` `/api/forms/{id}` | `FormController` | Form dados pessoais |
| GET/POST/PUT/DELETE | `/api/parentes` | `FormDadosParentesController` | Form dados parentes |
| GET/POST/PUT/DELETE | `/api/enderecos` | `FormEnderecoCandidatoController` | Endereço do candidato (+ `/por-cep/{cep}`) |
| GET/POST/PUT/DELETE | `/api/form-condicoes-habitacionais` | `FormCondicoesHabitacionaisController` | Condições habitacionais |
| POST/GET | `/api/bens-posses` | `BensPossesController` | Bens (veículos, escolas, PCD, despesas) |
| GET/POST/PUT/DELETE | `/api/processo-bolsas` | `ProcessoDeBolsaController` | Processo de bolsa |
| POST/GET | `/api/composicao-familiar` | `ComposicaoFamiliarController` | Composição familiar |
| GET/POST/DELETE | `/api/declaracoes` | `DeclaracaoController` | Declarações (⚠ tem **dois** `@GetMapping("/{id}")` conflitantes) |
| POST/GET/PUT/DELETE | `/api/parecer-socioeconomico` | `ParecerSocioeconomicoController` | CRUD do parecer |
| GET | `/api/parecer-socioeconomico/{id}/pdf-base64` | idem | Geração de PDF do parecer (HTML → PDF via OpenPDF) |
| POST | `/api/documentos-gerais-pdf/upload/{campo}` | `DocumentosGeraisPdfController` | Upload de PDF por campo |
| GET | `/api/documentos-gerais-pdf/download/{id}/{campo}` | idem | Download por campo |
| GET | `/api/documentos-gerais-pdf/download/list/{campo}` | idem | Download em massa (base64) |

**Observações sobre endpoints**:
- `DocumentosGeraisPdfController` usa um `switch (campo)` gigante (17 cases) que se repete em 2 endpoints — candidato a refactor (Map<String, Function<...>> ou estratégia).
- `DeclaracaoController` tem dois métodos com `@GetMapping("/{id})` — o segundo (`getDeclaracoesByUserId`) está mascarado.
- `SwaggerConfig` faz scan **apenas** de `pdev.com.agenda.domain.controller` → os controllers em `pdev.com.agenda.controller` (Composição, Declaração) **não aparecem no Swagger**.

---

## 5. Modelo de domínio (resumo)

```
UserInfo ─┬─ Role
          ├─ FormDadosPessoais (1:N hoje, mas semanticamente 1:1)
          ├─ FormDadosParentes (1:N)
          ├─ FormEnderecoCandidato (1:N)
          ├─ FormCondicoesHabitacionais (1:N)
          ├─ Endereco (1:N) ← antigo
          ├─ ProcessoDeBolsa (1:N)
          ├─ BensPosses (1:N)
          │   ├── Veiculo (1:N)
          │   ├── FamiliarEscolaParticular (1:N)  ← migrations duplicadas V16/V49/V51
          │   ├── PessoaComDeficiencia (1:N)
          │   └── DespesaMensal (1:N)
          ├─ DocumentosGeraisPdf (1:N, 17 colunas BYTEA)
          ├─ ComposicaoFamiliar (1:N)
          ├─ Declaracao (1:N)
          └─ ParecerSocioeconomico (1:N)

Paciente / Agenda / PacienteAgenda ← domínio legado (não usado nos fluxos atuais?)
```

Todas as entidades têm coluna `status: String` (string solta, sem enum). Adicionada em massa pela `V35__add_status_to_all_entities.sql`.

---

## 6. Dívida técnica conhecida

Esta seção é o **inventário de problemas** identificados na varredura. Use como checklist ao planejar mudanças.

### 6.1 Críticos (segurança)

| # | Item | Local |
|---|---|---|
| C1 | `/api/**` no `AUTH_WHITELIST` — toda a API está pública | `SecurityConfig.java:42` |
| C2 | Senhas armazenadas e comparadas em **texto puro** (`.equals()`) — `BCryptPasswordEncoder` é declarado mas não é usado em lugar nenhum | `AuthService.java:27`, `UserInfoService.resetPassword:170,174`, `PasswordEncoderConfig` órfão |
| C3 | **Senha do usuário logada** em `log.info` | `CustomAuthenticationFilterConfig.java:33` |
| C4 | Authorities/roles **não são carregados** no `SecurityContext` (`new UsernamePasswordAuthenticationToken(user, null, null)`) → `@PreAuthorize`/role-based authorization não funciona | `CustomAuthorizationFilterConfig.java:34` |
| C5 | Secret do JWT hardcoded em `application-dev.properties` | `application-dev.properties:16` |
| C6 | Senha do banco em texto plano em `application-local.properties` | linha 4 |
| C7 | Token JWT expira em **10 minutos** e é persistido no banco (anti-pattern para JWT stateless) | `TokenService.java:25`, `AuthService.java:31-32` |
| C8 | `successfulAuthentication` no `CustomAuthenticationFilterConfig` faz cast `(UserInfo) auth.getPrincipal()` — o `DaoAuthenticationProvider` devolve um `org.springframework.security.core.userdetails.User`, não `UserInfo` → o filtro de autenticação form-based provavelmente **nunca foi exercitado** (login real é via `AuthService`) | `CustomAuthenticationFilterConfig.java:40` |

### 6.2 Críticos (estabilidade / Flyway)

| # | Item | Local |
|---|---|---|
| F1 | `FlywayRepairRunner` executa `flyway.repair()` em `@PostConstruct` em todo startup — mascara divergências reais de checksum | `util/FlywayRepairRunner.java` |
| F2 | Três migrations tentam criar `familiar_escola_particular`: `V16`, `V49`, `V51` (V51 ao menos é idempotente) | `db/migration/` |
| F3 | `V32__flyway_repair.sql` contém apenas `--repair` (não é SQL válido — é por isso que F1 existe) | `db/migration/V32__flyway_repair.sql` |
| F4 | Nome de arquivo com **espaço**: `V4__insert _user_admin.sql` | `db/migration/` |
| F5 | `flyway.out-of-order=true` em `application.properties` (sem prefixo `spring.`) → ignorado pelo Boot | `application.properties:2` |

### 6.3 Arquitetura

| # | Item |
|---|---|
| A1 | Pacotes duplicados: `controller/` + `domain/controller/`, idem para `service`, `repository`, `dto`, `mapper`. Unificar |
| A2 | **MapStruct + ModelMapper** convivem fazendo a mesma coisa. Além disso, `UserInfoMapper` é **manual** (`@Component`), não usa MapStruct. Decidir padrão único |
| A3 | **Springfox** (sem manutenção) com workarounds; misturado com anotações `io.swagger.v3.oas` (Springdoc). Migrar para `springdoc-openapi` |
| A4 | `SwaggerConfig` scan limitado a `domain.controller` — controllers fora ficam invisíveis |
| A5 | Sem `@RestControllerAdvice` / `GlobalExceptionHandler` — exceções vazam stack trace |
| A6 | `BusinessException` é a única exceção customizada, sem hierarquia |
| A7 | `System.out.println` em produção (`FlywayRepairRunner`) — usar SLF4J |
| A8 | `@Autowired` em campo + construtor injetado misturados (boa parte usa `@RequiredArgsConstructor`, mas `AuthService` e `DeclaracaoController` ainda usam field injection) |
| A9 | Coluna `status` como `String` solta — deveria ser um enum (`StatusEnum.PENDENTE/EM_ANALISE/APROVADO/...`) |
| A10 | Pacote `enuns/` em vez de `enums/` (typo) |
| A11 | Pacote `pdev.com.agenda` invertido (convenção Java: `com.<empresa>.<projeto>`) |
| A12 | `DocumentosGeraisPdf` armazena **17 colunas BYTEA** numa única tabela — deveria ser tabela genérica `(user_id, tipo_documento, conteudo)` |
| A13 | `switch (campo)` duplicado em `DocumentosGeraisPdfController` (17 cases × 2 endpoints) |
| A14 | `DeclaracaoController` tem dois `@GetMapping("/{id}")` (path conflitante) |
| A15 | `UserInfo` tem coluna `token` (persistir JWT é anti-pattern) |
| A16 | Spring Cloud OpenFeign **3.0.7** sem BOM — risco de incompatibilidade com Boot 2.5.9 |
| A17 | `system.properties` aponta Java 11 mas pom usa Java 17 |
| A18 | `@EnableFeignClients` declarado mas **nenhum** `@FeignClient` foi encontrado no projeto — verificar se é usado |
| A19 | Tabela JPA `fomulario_dados_pessoais` (typo: "fomulario" → "formulario") |

### 6.4 Qualidade / Testes / Docs

| # | Item |
|---|---|
| Q1 | `src/test` praticamente vazio (apenas `application.properties`). Cobertura ≈ 0% |
| Q2 | Sem README.md, sem CONTRIBUTING.md, sem ADRs |
| Q3 | Histórico recente do git tem **5 commits idênticos** (`create familiar_escola_particular table with foreign key constraint`) |
| Q4 | CORS lista hosts de produção em código (`https://educabolsas.netlify.app`, `https://asap-api-production.up.railway.app`) — deveria ser configurável por profile |
| Q5 | Mistura `@AllArgsConstructor` no controller com `@Autowired` no service — padronizar (preferir `@RequiredArgsConstructor` com `final`) |
| Q6 | Mistura idioma PT/EN em colunas, métodos, DTOs (`fullName`, `nomeCompleto`, `commutingTime`, `dataNascimento`) |

---

## 7. Comandos úteis

### Subir banco local
```bash
docker compose up -d db pgadmin
# Postgres em localhost:5432 (user: postgres / pass: postgres / db: test_db)
# pgAdmin em http://localhost:15432 (admin@admin.com / root)
```

### Rodar a aplicação localmente
```bash
# Profile 'local' (padrão) — usa application-local.properties (URL: localhost:5432/postgres, pass=123456)
./mvnw spring-boot:run

# Profile 'dev' — exige env vars PG_HOST, PG_PORT, PG_DATABASE, PG_USER, PG_PASSWORD
APP_PROFILE=dev ./mvnw spring-boot:run
```

> Cuidado: o `docker-compose.yml` cria DB `test_db`, mas `application-local.properties` aponta para DB `postgres`. Ajustar antes de subir.

### Build
```bash
./mvnw clean install        # build com testes (testes praticamente inexistentes)
./mvnw clean package -DskipTests
```

### Docker
```bash
docker build -t agenda-service .
docker run -p 8080:8080 \
  -e APP_PROFILE=dev \
  -e PG_HOST=... -e PG_PORT=5432 -e PG_DATABASE=... \
  -e PG_USER=... -e PG_PASSWORD=... \
  agenda-service
```

### Swagger
- Springfox 3 + Boot 2.5: pode exigir `spring.mvc.pathmatch.matching-strategy=ant_path_matcher` para funcionar
- URL típica: `http://localhost:8080/swagger-ui/`

---

## 8. Convenções a respeitar ao mexer

1. **Não rode `flyway.repair()` programaticamente** — qualquer correção de checksum deve ser explícita e revisada
2. **Não crie nova migration com nome já existente** — `V<N>__<descricao>.sql`, `<N>` único e sequencial
3. **Ao tocar `SecurityConfig`**, valide com o front (educabolsas) antes de fechar `/api/**`
4. **Ao adicionar nova feature**, escolha um lado dos pacotes paralelos (ideal: tudo em `domain/`) — mas alinhe antes
5. **DTOs** ficam separados de entidades — use MapStruct (não ModelMapper) para novos mappers
6. **Não persista senha em texto puro** — qualquer fluxo novo de senha deve passar por `PasswordEncoder` (já existe o bean)
7. **Não logue credenciais** (`log.info` com senha/token)
8. **Não persista JWT** — JWT é stateless por design
9. **Status de entidades**: por hora a string `"PENDENTE"` é usada como default — manter consistente até migrarmos para enum

---

## 9. Plano de melhoria (estado, atualizar à medida que avançar)

> Este plano foi alinhado entre o usuário e o agente. Cada parte é um conjunto coeso, com risco baixo→médio, executada e revisada antes de seguir.

| # | Parte | Status | Notas |
|---|---|---|---|
| 1 | Estabilizar Flyway (F1–F5) | 🟡 parcial | V52 e V53 adicionadas (alinhamento `familiar_escola_particular` + nova feature). Resto (F1, F3, F4) pendente |
| 2 | Corrigir falha de segurança (C1, C2, C3, C5, C6) | ⏳ pendente | depende de saber endpoints usados pelo front |
| 3 | **Documentação base (CLAUDE.md + README.md)** | ✅ entregue | |
| 4 | Unificar estrutura de pacotes (A1, A10, A11) | ⏳ pendente | refactor amplo — fazer só depois de testes mínimos |
| 5 | Higiene de dependências (A2, A3, A16, A17) | ⏳ pendente | |
| 6 | `GlobalExceptionHandler` + SLF4J (A5, A7) | ⏳ pendente | |
| 7 | Testes mínimos (Q1) | ⏳ pendente | criar suíte base por slice (`@WebMvcTest`, `@DataJpaTest`) |
| 8 | (Opcional) Upgrade Spring Boot 2.5.9 → 3.x | ⏳ pendente | maior risco — só com testes em pé |

---

## 10. Histórico de mudanças aplicadas

> Quando uma dívida da §6 for resolvida, mova o item pra cá com referência ao commit/migration.

### 2026-05-28 — Modernização do back (padrões adotados)

- **MapStruct ativado de verdade**: `UserInfoMapper` migrado de `@Component` manual para `@Mapper(componentModel = "spring")` interface — agora é o template para futuros mappers (ver §12.1)
- **`pom.xml`**: adicionado `annotationProcessorPaths` explícito (Lombok 1.18.34 + MapStruct 1.5.5 + `lombok-mapstruct-binding 0.2.0`) para garantir geração correta com Lombok presente
- **`GlobalExceptionHandler`** criado (`@RestControllerAdvice`): mapeia 7 tipos de exceção para HTTP status apropriados, com `ErrorResponse` padronizado (ver §12.3)
- **`ErrorResponse`** DTO criado: estrutura `timestamp + status + error + message + path + fieldErrors[]`
- **Bean Validation** ativada em `UserInfoDTO`: `@NotBlank`, `@Email`, `@Pattern`
- **`UserInfoController`** limpo: `@Valid` no `@RequestBody`, sem try/catch (delega para handler), `@RequiredArgsConstructor`, POST retorna `201 Created`
- **`UserInfoService`**:
  - Resolução de `Role` movida do mapper para o service (separação de concerns — ver §12.4)
  - `validateUserInfo` foi quebrado em `validateForCreate` (checa duplicidade) e `validateForUpdate` (ignora o próprio user via `existsByEmailAndIdNot`)
  - Bug corrigido: update agora funciona (antes sempre falhava com "E-mail já cadastrado")
  - Constante `DEFAULT_STATUS = "PENDENTE"` extraída
- **`UserInfoRepository`**: novos métodos `existsByEmailAndIdNot` / `existsByUserNameAndIdNot` para validação no update
- **Dívida resolvida** (parcial):
  - A2 — MapStruct agora é o padrão; ModelMapper continua presente mas não usado em código novo
  - A5 — GlobalExceptionHandler criado
  - A8 — field injection removida do UserInfoController (migrou para `@RequiredArgsConstructor`)

### 2026-05-27 — Feature `tipoAluno` no UserInfo + alinhamento `familiar_escola_particular`

- **Novo enum**: `TipoAlunoEnum` (`ESCOLA_PARTICULAR`, `ESCOLA_GRATUITA`) em `domain/enuns/`
- **Migration `V52__add_tipo_aluno_to_user_info.sql`**: adiciona coluna `tipo_aluno VARCHAR(30) NULL` em `user_info`. Usuários existentes (194 alunos) ficam com `tipo_aluno = NULL`
- **Migration `V53__align_familiar_escola_particular.sql`**: adiciona colunas faltantes `valor_mensal NUMERIC(19,2)` e `status VARCHAR(50)` para alinhar com a entidade JPA. Mantém `parentesco` como legado nullable
- **Entidade `UserInfo`**: novo campo `tipoAluno: TipoAlunoEnum` com `@Enumerated(EnumType.STRING)`
- **DTOs**: `UserInfoDTO` e `UserInfoResponse` ganharam o campo `tipoAluno`
- **`UserInfoMapper`**: mapeia `tipoAluno` em `toEntity`, `toDTO` e `toResponseDTO`
- **`UserInfoService`**:
  - `validateTipoAluno()` — obrigatório quando `roleName = ROLE_USER`
  - `normalizeTipoAlunoForAdmin()` — força `null` quando `roleName = ROLE_ADMIN`
  - Aplicado em `create()` e `update()`
- **`pom.xml`**: override do Lombok para `1.18.34` (compatibilidade com JDK 17/21+)
- **Dívida resolvida**: parte da F2 (schema `familiar_escola_particular`) — restam V16 antiga não-reproduzível e parentesco legado para limpar depois
- **Pendente para o front**: modal "Criar Novo Usuário" precisa enviar `tipoAluno` quando perfil = Aluno. Sem isso, o POST passa a retornar 400

---

## 11. Convenção: `tipoAluno` no fluxo de criação de usuário

Quando o admin chama `POST /api/users`:

| `roleName` enviado | `tipoAluno` esperado | Comportamento |
|---|---|---|
| `ROLE_USER` | obrigatório (`ESCOLA_PARTICULAR` ou `ESCOLA_GRATUITA`) | Validado em `UserInfoService.validateTipoAluno()` — lança `IllegalArgumentException` (400) se ausente |
| `ROLE_ADMIN` | ignorado | Forçado a `null` pelo `normalizeTipoAlunoForAdmin()` |

**Payload de exemplo (criar aluno):**
```json
{
  "name": "Aluno Teste",
  "cpf": "12345678901",
  "email": "aluno@example.com",
  "roleName": "ROLE_USER",
  "tipoAluno": "ESCOLA_PARTICULAR"
}
```

**Payload de exemplo (criar admin):**
```json
{
  "name": "Admin Teste",
  "cpf": "98765432100",
  "email": "admin@example.com",
  "roleName": "ROLE_ADMIN"
}
```

> O username e a senha inicial do usuário são automaticamente preenchidos com o CPF — comportamento herdado do `UserInfoMapper.toEntity()`. Isso é parte da dívida de segurança C2 (senha em texto puro) a ser endereçada em outra parte.

---

## 12. Convenções de código (padrão a partir de 2026-05-28)

> Estes são os padrões adotados após a primeira rodada de modernização do back. **Todo código novo a partir dessa data deve seguir esses padrões**. Refatorações de código legado devem ser planejadas separadamente.

### 12.1 Mappers — MapStruct (obrigatório para mappers novos)

**Padrão de referência**: `pdev.com.agenda.domain.mapper.UserInfoMapper`

```java
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface XxxMapper {

    @Mapping(target = "id", source = "userId")
    XxxEntity toEntity(XxxDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(XxxDTO dto, @MappingTarget XxxEntity entity);

    XxxDTO toDTO(XxxEntity entity);

    XxxResponse toResponseDTO(XxxEntity entity);
}
```

**Regras**:
- `componentModel = "spring"` — gera bean Spring (injetar via `@RequiredArgsConstructor`)
- `unmappedTargetPolicy = IGNORE` — não falha por campo não mapeado (sempre seja explícito com `@Mapping`)
- **Mapper é puro** — não chama repositório, não faz I/O. Resolução de relacionamentos (FK) é responsabilidade do **service**
- Para update parcial, use `updateEntityFromDto(@MappingTarget)` com `nullValuePropertyMappingStrategy = IGNORE`
- Para boolean fields com prefixo `is` (ex: `isActive`, `isFirstLogin`) **e** classe DTO com `@Builder`: use `target = "isActive"` (não `"active"`), porque o método do builder mantém o prefixo
- **Não migrar mappers antigos** "de graça" — só refatorar quando estiver mexendo no arquivo

### 12.2 Validação — Bean Validation no DTO + `@Valid` no controller

**No DTO**:
```java
@NotBlank(message = "O nome é obrigatório.")
private String name;

@Email(message = "E-mail inválido.")
@NotBlank(message = "O e-mail é obrigatório.")
private String email;

@Pattern(regexp = "ROLE_USER|ROLE_ADMIN", message = "Perfil inválido.")
private String roleName;
```

**No controller**:
```java
@PostMapping
public ResponseEntity<XxxDTO> create(@Valid @RequestBody XxxDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
}
```

**Não use** try/catch no controller — o `GlobalExceptionHandler` cuida disso.

### 12.3 Tratamento de erros — `GlobalExceptionHandler`

Centralizado em `pdev.com.agenda.exception.GlobalExceptionHandler` (`@RestControllerAdvice`).
Mapeamento padrão:

| Exception lançada pelo service | HTTP Status | Quando usar |
|---|---|---|
| `IllegalArgumentException` | 400 | Validação simples (formato inválido, valor não permitido) |
| `MethodArgumentNotValidException` | 400 | Automático — Bean Validation falhou (vem com `fieldErrors`) |
| `EntityNotFoundException` | 404 | Recurso não encontrado |
| `UsernameNotFoundException` | 404 | Login com user inexistente |
| `BadCredentialsException` | 401 | Senha incorreta |
| `BusinessException` | 422 | Regra de negócio violada (estado inválido para a operação) |
| `Exception` (fallback) | 500 | Bug — logado em ERROR |

**Resposta padronizada** (`ErrorResponse`):
```json
{
  "timestamp": "2026-05-28T10:15:30-03:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Erro de validação nos campos enviados.",
  "path": "/api/users",
  "fieldErrors": [{ "field": "email", "message": "E-mail inválido." }]
}
```

### 12.4 Service — separação de concerns

**Padrão**:
1. **Validação** primeiro (`validateForCreate`, `validateForUpdate`, regras de negócio)
2. **Normalização** (ex: `normalizeTipoAlunoForAdmin`)
3. **Mapping** (DTO → Entity via MapStruct)
4. **Resolução de FK** (Role, etc.) — separar essa etapa do mapping
5. **Persistir**
6. **Mapping** de volta (Entity → DTO)

```java
public XxxDTO create(XxxDTO dto) {
    validateForCreate(dto);
    normalizeIfNeeded(dto);
    XxxEntity entity = mapper.toEntity(dto);
    entity.setFk(resolveFkFromService(dto));
    return mapper.toDTO(repository.save(entity));
}
```

Para update: use `mapper.updateEntityFromDto(dto, entity)` com partial update.

### 12.5 Injeção de dependências

- **Sempre** `@RequiredArgsConstructor` (Lombok) — não use `@AllArgsConstructor` no controller
- Campos `private final` — imutáveis
- **Nunca** `@Autowired` em campo (legacy)

### 12.6 Controller

- `@RestController` + `@RequestMapping("/api/...")`
- Métodos retornam `ResponseEntity<Tipo>` (status correto: 201 Created para POST, 200 OK para GET/PUT, 204 No Content para DELETE)
- `@Valid` em todo `@RequestBody`
- **Sem** try/catch — delega para `GlobalExceptionHandler`
- Sem lógica de negócio (delega para service)

---

## 13. Para o próximo agente

- Sempre **leia `CLAUDE.md` antes de propor mudanças**
- Use o `TaskCreate`/`TaskUpdate` para tracking de etapas longas
- Antes de tocar Flyway, **rode `select version, script, checksum, success from flyway_schema_history order by installed_rank`** no banco e mostre ao usuário
- Antes de tocar `SecurityConfig.AUTH_WHITELIST`, **confirme com o usuário** quais endpoints o front consome sem token (login é obrigatório aberto; o restante deveria exigir Bearer)
- Mantenha esta seção `## 6. Dívida técnica conhecida` atualizada — quando uma dívida for paga, mova para uma seção "Histórico" no fim do arquivo
