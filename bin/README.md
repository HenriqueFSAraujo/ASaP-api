# agenda-service

API Spring Boot para **Cadastro Social de Bolsas** — coleta de dados socioeconômicos de candidatos e geração de parecer.

> Para contexto técnico profundo, dívida técnica e plano de melhorias, leia **[`CLAUDE.md`](./CLAUDE.md)**.

---

## Stack

- Java 17 · Spring Boot 2.5.9 · Maven
- PostgreSQL · Flyway
- Spring Security + JWT (java-jwt)
- MapStruct · OpenPDF · OpenFeign · Springfox

---

## Como rodar localmente

### 1. Subir o banco

```bash
docker compose up -d db pgadmin
```

- PostgreSQL: `localhost:5432` — user `postgres` / senha `postgres` / db `test_db`
- pgAdmin: `http://localhost:15432` — `admin@admin.com` / `root`

> ⚠ `application-local.properties` aponta para o database `postgres`, não `test_db`. Crie o banco `postgres` no Postgres do compose, ou ajuste a URL antes de rodar.

### 2. Rodar a aplicação

```bash
# Perfil padrão (local)
./mvnw spring-boot:run
```

Ou via Docker:

```bash
docker build -t agenda-service .
docker run -p 8080:8080 agenda-service
```

A API sobe em `http://localhost:8080`.

### 3. Profiles disponíveis

| Profile | Arquivo | Origem das credenciais |
|---|---|---|
| `local` (padrão) | `application-local.properties` | Hardcoded — `postgres`/`123456` |
| `dev` | `application-dev.properties` | Variáveis de ambiente `PG_HOST`, `PG_PORT`, `PG_DATABASE`, `PG_USER`, `PG_PASSWORD` |

Trocar profile:
```bash
APP_PROFILE=dev ./mvnw spring-boot:run
```

---

## Build

```bash
./mvnw clean package                  # com testes
./mvnw clean package -DskipTests      # sem testes
```

O JAR final fica em `target/agenda-0.0.1-SNAPSHOT.jar`.

---

## Documentação da API

Swagger UI (Springfox 3): `http://localhost:8080/swagger-ui/`

> Nota: alguns controllers ficam fora do scan atual do Swagger. Ver `CLAUDE.md §6.3 A4`.

---

## Fluxos principais

1. **Login** — `POST /api/auth/login` → recebe `{ userName, password }`, retorna JWT
2. **Cadastro de candidato** — admin cria `UserInfo` via `POST /api/users`
3. **Preenchimento de formulários** — usuário envia dados em múltiplos endpoints (`/api/forms`, `/api/parentes`, `/api/enderecos`, `/api/bens-posses`, `/api/composicao-familiar`, `/api/declaracoes`, etc.)
4. **Upload de documentos** — `POST /api/documentos-gerais-pdf/upload/{campo}`
5. **Parecer socioeconômico** — `POST /api/parecer-socioeconomico` cria; `GET /api/parecer-socioeconomico/{id}/pdf-base64` gera PDF

Lista completa de endpoints em [`CLAUDE.md §4`](./CLAUDE.md).

---

## Estrutura

```
src/main/java/pdev/com/agenda/
├── AgendaApplication.java
├── config/                 ← Security, JWT, Swagger, ModelMapper
├── controller/             ← Controllers novos
├── service/  repository/  dto/  mapper/
├── domain/                 ← Maioria dos controllers, services, entidades
│   ├── controller/  service/  repository/  entity/  dto/  mapper/  enuns/
├── exception/
└── util/

src/main/resources/
├── db/migration/           ← 51 migrations Flyway
├── templates/              ← Template HTML do parecer
└── application*.properties
```

> A estrutura está em transição. Pacotes paralelos em `controller/` e `domain/controller/` (idem para service, repository, etc.) — ver `CLAUDE.md §3` e plano de unificação na §9.

---

## Migrations

Flyway gerencia o schema. Para criar uma migration nova:

1. Crie `src/main/resources/db/migration/V<N>__<descricao>.sql` com `N` único e sequencial (último atual: **V51**)
2. **Não reutilize nomes de migrations já aplicadas**
3. **Não rode `flyway.repair()`** — há um runner em `util/FlywayRepairRunner.java` que faz isso a cada startup; será removido (ver dívida F1)

---

## Testes

A suíte de testes está praticamente vazia hoje. Há trabalho planejado para criar uma base mínima — ver `CLAUDE.md §9 Parte 7`.

```bash
./mvnw test
```

---

## Status e plano de melhorias

Este projeto tem dívida técnica significativa **mapeada e priorizada** em [`CLAUDE.md`](./CLAUDE.md). Antes de abrir PR, leia as seções:

- §6 — Dívida técnica conhecida (críticos de segurança e Flyway)
- §8 — Convenções a respeitar
- §9 — Plano de melhoria

---

## Contato

Henrique Araujo — rickfer2010@gmail.com
