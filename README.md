# CF-AuthUser-back
Este é o backend do sistema de autenticação de usuários, CF-AuthUser, desenvolvido em Java com Spring Boot.
Ele oferece funcionalidades para cadastro, autenticação e gerenciamento de usuários.

## Tecnologias Utilizadas
* Java 17
* Spring Boot
* Maven
* PostgreSQL
* Docker
* Jacoco (para cobertura de testes)
* JUnit 5 (para testes unitários)
* GitHub Actions (para CI/CD)

## Funcionalidades
* Autenticação de Usuário: Cadastro, login, e exclusão de contas.
* CRUD de Usuários: Criação, leitura, atualização e deleção de dados de usuários.
* Pipeline CI/CD: Build automatizado e execução de testes com GitHub Actions.

### Estrutura do Projeto
controllers: Controladores que lidam com as requisições HTTP.
services: Lógica de negócio da aplicação.
models: Modelos que representam as entidades do sistema.
dtos: Objetos de transferência de dados usados nas requisições e respostas.
enums: Enumerações utilizadas no projeto.
repositories: Interfaces que comunicam-se com o banco de dados.

### Requisitos
Java 17+
Maven 3.6+
PostgreSQL 13+
Docker (para execução local com containers)
Git

## Instalação
