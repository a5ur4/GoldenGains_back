# Golden Gains

**Golden Gains** é uma aplicação backend desenvolvida para gerenciar e acompanhar diversos aspectos do mundo fitness, incluindo dados pessoais, levantamentos, rankings, músicas, notícias, postagens e mais. O projeto é construído com Java e Spring Boot.

## Funcionalidades

- Autenticação com JWT
- Gerenciamento de usuários e permissões (ADMIN / USER)
- Cadastro e acompanhamento de dados pessoais
- Sistema de postagens por categoria
- Acompanhamento de músicas, notícias, levantamentos e rankings

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Hibernate / JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**
- **Spring Security + JWT**

## Requisitos

- Java 21
- Maven
- PostgreSQL

## Instalação

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/golden-gains.git
cd golden-gains
```

2. Configure o banco de dados:

- Crie um banco PostgreSQL (ex: `goldengains`)
- Atualize o arquivo `application.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/goldengains
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

3. Construa o projeto:

```bash
mvn clean install
```

4. Execute a aplicação:

```bash
mvn spring-boot:run
```

5. Acesse a API em `http://localhost:8080`

---

## Endpoints da API

> Os endpoints seguem controle de acesso por papéis (`USER` e `ADMIN`), com autenticação via JWT.

### Autenticação (`/auth`)
- `POST /auth/register`
- `POST /auth/authenticate`

---

### Usuários (`/users`)
- `GET /users/get_all` — USER / ADMIN  
- `GET /users/get_by_id/{id}` — USER / ADMIN  
- `GET /users/get_by_email/{email}` — USER / ADMIN  
- `PUT /users/update/{id}` — ADMIN  
- `PUT /users/update_role/{id}` — ADMIN  
- `DELETE /users/delete/{id}` — ADMIN  

---

### Dados Pessoais (`/personal-data`)
- `GET /personal-data/get_all` - Público
- `GET /personal-data/get_by_id/{id}` - Público
- `POST /personal-data/create` — USER / ADMIN  
- `PUT /personal-data/update/{id}` — USER / ADMIN  
- `DELETE /personal-data/delete/{id}` — USER / ADMIN  

---

### Categorias (`/categories`)
- `GET /categories/get_all` — Público  
- `GET /categories/get_by_id/{id}` — Público  
- `POST /categories/create` — USER / ADMIN  
- `PUT /categories/update/{id}` — USER / ADMIN  
- `DELETE /categories/delete/{id}` — USER / ADMIN  

---

### Postagens (`/posts`)
- `GET /posts/get_all` — Público  
- `GET /posts/get_by_id/{id}` — Público  
- `GET /posts/get_by_user/{id}` — Público  
- `GET /posts/get_by_category/{id}` — Público  
- `POST /posts/create` — USER / ADMIN  
- `PUT /posts/update/{id}` — USER / ADMIN  
- `DELETE /posts/delete/{id}` — USER / ADMIN  

---

### Notícias (`/news`)
- `GET /news/get_all` — Público  
- `GET /news/get_by_id/{id}` — Público  
- `GET /news/get_by_category/{id}` — Público  
- `POST /news/create` — USER / ADMIN  
- `PUT /news/update/{id}` — USER / ADMIN  
- `DELETE /news/delete/{id}` — USER / ADMIN  

---

### Músicas (`/music`)
- `GET /music/get_all` — Público  
- `GET /music/get_by_id/{id}` — Público  
- `GET /music/get_by_user/{id}` — Público  
- `POST /music/create` — USER / ADMIN  
- `PUT /music/update/{id}` — USER / ADMIN  
- `DELETE /music/delete/{id}` — USER / ADMIN  

---

### Levantamentos (`/lifts`)
- `GET /lifts/get_all` — Público  
- `GET /lifts/get_by_id/{id}` — Público  
- `GET /lifts/get_by_user/{id}` — Público  
- `POST /lifts/create` — USER / ADMIN  
- `PUT /lifts/update/{id}` — USER / ADMIN  
- `DELETE /lifts/delete/{id}` — USER / ADMIN  

---

### Rankings (`/ranks`)
- `GET /ranks/get_all` — Público  
- `GET /ranks/get_by_id/{id}` — Público  
- `GET /ranks/get_by_user/{id}` — Público  
- `GET /ranks/get_by_lift/{id}` — Público  
- `POST /ranks/create` — USER / ADMIN  
- `PUT /ranks/update/{id}` — USER / ADMIN  
- `DELETE /ranks/delete/{id}` — USER / ADMIN  

---

## Contribuição

Contribuições são bem-vindas! Faça um fork do projeto, crie uma branch e envie um Pull Request.

---

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

---

## Contato

Abra uma [issue](https://github.com/a5ur4/GoldenGains_back/issues) ou entre em contato via [GitHub](https://github.com/a5ur4).
