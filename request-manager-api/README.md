# Request Manager API

API REST para gerenciamento de solicitações de pagamento, desenvolvida com Spring Boot 4.0.6 e Java 21.

## 🛠️ Tech Stack

- **Spring Boot** 4.0.6 - Framework web Java
- **Java** 21 - Linguagem de programação
- **Spring Data JPA** - Acesso a dados
- **Spring Validation** - Validação de dados
- **Maven** - Gerenciador de dependências e build

## 📋 Requisitos

- **Java 21** - JDK instalado
- **Maven** - Incluído no projeto como Maven Wrapper (`mvnw`)

## 🚀 Configuração e Execução

### 1. Build do Projeto (Primeira Vez)

```bash
# Compile e baixe todas as dependências
./mvnw clean install
```

### 2. Executar a API

```bash
# Inicia o servidor Spring Boot
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

### 3. Build para Produção

```bash
# Cria um JAR executável
./mvnw clean package

# Executa o JAR
java -jar target/request-manager-api-0.0.1-SNAPSHOT.jar
```

## 📚 Scripts Disponíveis

| Comando | Descrição |
|---------|-----------|
| `./mvnw clean install` | Compila e testa o projeto, instala dependências |
| `./mvnw spring-boot:run` | Inicia a aplicação em desenvolvimento |
| `./mvnw test` | Executa os testes unitários |
| `./mvnw clean package` | Cria um JAR executável |

## 🏗️ Estrutura do Projeto

```
src/main/java/com/gabrielx47/request_manager_api/
├── RequestManagerApiApplication.java  # Classe principal
├── controller/
│   ├── CategoriaController.java       # Endpoints de Categorias
│   ├── SolicitacaoController.java     # Endpoints de Solicitações
│   ├── SolicitanteController.java     # Endpoints de Solicitantes
│   └── UsuarioController.java         # Endpoints de Usuários
├── dto/                               # Data Transfer Objects
│   ├── CategoriaDTO.java
│   ├── NovaCategoriaDTO.java
│   ├── SolicitacaoDTO.java
│   ├── SolicitacaoCompletaDTO.java
│   ├── SolicitacaoListagemDTO.java
│   ├── SolicitacaoStatusDTO.java
│   ├── SolicitanteDTO.java
│   └── UsuarioDTO.java
├── model/
│   └── entity/                        # Entidades JPA
├── repository/                        # Camada de dados
├── service/                           # Lógica de negócio
├── exception/                         # Tratamento de exceções
└── security/                          # Configuração de segurança

src/main/resources/
├── application.properties             # Configurações da aplicação
└── data.sql                           # Script de dados iniciais
```

## 🔧 Configuração

### application.properties

As configurações principais estão em `src/main/resources/application.properties`:

```properties
# Porta do servidor
server.port=8080

# Banco de dados
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update

# JPA/Hibernate
spring.jpa.show-sql=false
```

Para alterar a porta, adicione ao arquivo:
```properties
server.port=8081
```

## 🔌 Endpoints Principais

### Solicitações
- `GET /api/solicitacoes` - Listar todas as solicitações
- `GET /api/solicitacoes/{id}` - Obter uma solicitação específica
- `POST /api/solicitacoes` - Criar nova solicitação
- `PUT /api/solicitacoes/{id}` - Atualizar solicitação
- `DELETE /api/solicitacoes/{id}` - Deletar solicitação

### Categorias
- `GET /api/categorias` - Listar categorias
- `POST /api/categorias` - Criar categoria

### Solicitantes
- `GET /api/solicitantes` - Listar solicitantes
- `POST /api/solicitantes` - Criar solicitante

### Usuários
- `GET /api/usuarios` - Listar usuários
- `POST /api/usuarios` - Criar usuário

## 🐛 Troubleshooting

### Problema: "Porta 8080 já em uso"
**Solução 1**: Mude a porta no `application.properties`:
```properties
server.port=8081
```

**Solução 2**: Mate o processo usando a porta:
```bash
# Linux/Mac
lsof -ti:8080 | xargs kill -9

# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

### Problema: "Maven command not found"
**Solução**: Use o Maven Wrapper incluído:
```bash
./mvnw clean install  # Linux/Mac
mvnw.cmd clean install  # Windows
```

### Problema: "Java version mismatch"
**Solução**: Verifique sua versão Java:
```bash
java -version
```
Deve ser Java 21 ou superior. [Baixe aqui](https://www.oracle.com/java/technologies/downloads/)

### Problema: "Erro ao compilar"
**Solução**: Limpe o cache e reinstale:
```bash
./mvnw clean install -U
```

## 📖 Referências

- [Documentação Spring Boot 4.0](https://docs.spring.io/spring-boot/4.0.6/reference/html/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/)
- [Java 21 Documentation](https://docs.oracle.com/en/java/javase/21/)

## 🔌 Integração Frontend

Esta API é consumida pela aplicação Vue.js em `../payment-request-manager-app/`. 

Para desenvolver ambas simultaneamente:
```bash
# Terminal 1 - API
cd request-manager-api
./mvnw spring-boot:run

# Terminal 2 - Frontend
cd payment-request-manager-app
npm run dev
```

## 👨‍💻 Desenvolvimento

Para instruções de execução completa, veja o [README Principal](../README.md)
