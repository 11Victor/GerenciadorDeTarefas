<h1 align="center">
	<img src="https://i.imgur.com/USFFl6m.png"  alt="Logo"  width="200"><br><br>
    Gerenciador de Tarefas
</h1>

<div>
    <p align="center">
    <a href="https://www.linkedin.com/in/victor-valencio-854012209/" target="_blank">
        <img src="https://img.shields.io/static/v1?label=Author&message=Victor Valencio&color=00ba6d&style=for-the-badge&logo=LinkedIn" alt="Author: Victor">
    </a>
    <a href="#">
		<img  src="https://img.shields.io/static/v1?label=Language&message=Java&color=red&style=for-the-badge&logo=Java"  alt="Language: Java">
	</a>
    </p>
</div>

## Table of Contents

<p align="center">
 <a href="#about">About</a> •
 <a href="#features">Features</a> •
 <a href="#technologies">Technologies</a> • 
 <a href="#documentation">Documentation</a> •
 <a href="#installation ">Installation </a> •
 <a href="#getting-started">Get Started</a> •
 <a href="#postgre-sql">PostgreSQL</a>
 
</p>

<br>

## 📌About

<div>
    <p align="center">
    API-REST de gerenciamento de tarefas com três tabelas(Pessoa, Tarefa e Departamento)
    </p>
</div>

<br>

## 🚀Features

- Criar, Editar e Apagar (Pessoa, Tarefa e Departamento)
- Alocar uma pessoa na tarefa que tenha o mesmo departamento (put/tarefas/alocar/{id})
- Finalizar a tarefa (put/tarefas/finalizar/{id})
- Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas.(get/pessoas)
- Buscar pessoas por nome e retorna média de horas gastas por tarefa. (get/pessoas/gastos)
- Listar departamento e quantidade de pessoas e tarefas (get/departamentos)

<br>

## 🌐Technologies

- Java 8
- JPA
- Maven
- Spring Boot
- Swagger
- PostgreSQL
- Postman

<br>

## 📃Documentation

- Link Documentação Swagger: https://tasks-documentacao.netlify.app/

<br>

## 📕Installation
**Você já deve ter instalado os seguintes programas**
- Docker

**Recomendações**
- É recomendável que você tenha instalado o Google Chrome ou Edge
- Eu recomendo usar o Eclipse como IDE de desenvolvimento

**A instalação e inicialização são 3 etapas!**
1. Clone este repositório
2. Entre na pasta descompactada
3. Rode o projeto com o Docker Compose

### 1. Clone this repository
```
git clone https://github.com/11Victor/GerenciadorDeTarefas.git
```
---

### 2. Acesse a pasta descompactada pelo terminal
```
cd <caminho da pasta>
```
---

### 3. Inicialize o projeto com o Docker Compose
```
docker-compose up
```


<br>

## 🎮Getting Started
- Abra o navegador e entre no seguinte link: http://localhost:8080
    - O link acima irá abrir o Swagger do projeto em questão!

<br>

## 🗂️Postgre SQL
**Para utilizar o banco de dados siga os seguintes passos**

### 1. Rode o comando abaixo no Terminal e copie o CONTAINER ID do postgres
```
docker ps
```
---
### 2. Entre no banco de dados com o comando abaixo (Obs: substitua o CONTAINER ID)
```
docker exec -it ContainerID psql -U postgres tasks
```
---
<br>

**Alguns comandos do PostgreSQL**
### Listar todas tabelas
```
\dt
```
---
### Listar tabela departamento
```
select * from tb_departamento;
```