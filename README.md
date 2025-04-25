# Banking Management API

API simples de gestão bancária desenvolvida com Java 21 e Spring Boot.


## Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Flyway (migrations)
- Docker & Docker Compose
- Lombok
- Swagger (OpenAPI)


## Como executar a aplicação

### Variáveis de Ambiente

Para o ambiente de desenvolvimento, adicione um arquivo .env na raiz do projeto, com as seguintes variáveis:

```
POSTGRES_DB=devdb
POSTGRES_USER=devuser
POSTGRES_PASSWORD=devpass

# Esse é o endereço do gateway do container. Caso execute a aplicação fora do container, trocar para localhost.
SPRING_DATASOURCE_URL=jdbc:postgresql://172.18.0.1:5432/devdb 

SPRING_DATASOURCE_USERNAME=devuser
SPRING_DATASOURCE_PASSWORD=devpass

SPRING_DATASOURCE_HIKARI_MINIMUM_IDLE=5
SPRING_DATASOURCE_HIKARI_MAXIMUM_POOL_SIZE=10
SPRING_DATASOURCE_HIKARI_IDLE_TIMEOUT=600000
SPRING_DATASOURCE_HIKARI_MAX_LIFETIME=1800000
SPRING_DATASOURCE_HIKARI_CONNECTION_TIMEOUT=30000
```

---

### Rodando com Docker Compose

1. **Pré-requisitos:** Docker e Docker Compose instalados.
2. **Comando:**

```bash
docker-compose up --build
```

A API estará acessível em: [http://localhost:8080/api](http://localhost:8080/api)  
Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

O Docker Compose irá:
- Subir o container do banco PostgreSQL
- Buildar a imagem da aplicação
- Aplicar as migrations com Flyway

---

### Rodando via linha de comando

1. **Pré-requisitos:** Java 21 e Maven instalados.
2. Altere as variáveis no application.properties
3. **Compile e rode:**

```bash
./mvnw spring-boot:run ou mvn spring-boot:run
```

---

### Rodando no VS Code

1. Instale as extensões:
   - Java Extension Pack
   - Debugger for Java
   - Test Runner for Java (opcional)

2. Configure seu `launch.json`, não esquecendo de adicionar o .env na raiz do projeto:

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "BankingApplication",
            "request": "launch",
            "mainClass": "com.management.banking.BankingApplication",
            "envFile": "${workspaceFolder}/.env"
        }
    ]
}
```

3. Rode pressionando `F5`, ou clique na opção "Start Debugging".


## Rodando Testes

```bash
./mvnw test
```

