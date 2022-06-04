[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=GerenciadorDeTarefas&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=GerenciadorDeTarefas)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=GerenciadorDeTarefas&metric=bugs)](https://sonarcloud.io/summary/new_code?id=GerenciadorDeTarefas)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=GerenciadorDeTarefas&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=GerenciadorDeTarefas)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=GerenciadorDeTarefas&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=GerenciadorDeTarefas)

<br><br>

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
 <a href="#postgresql">PostgreSQL</a>
 
</p>

<br>

## 📌About

<div>
    <p align="center">
    API-REST de gerenciamento de tarefas, analise de código com SonarQube e deploy realizado na AWS EC2!
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
- SonarQube
- AWS-EC2

<br>

## 📃Documentation

- Link Documentação Swagger: https://tasks-documentacao.netlify.app/

<br>

## 📕Installation
**Você já deve ter instalado os seguintes programas**
- Docker <a href="https://docs.docker.com/get-docker/">(Veja como você instala o Docker)</a>
- Maven <a href="https://maven.apache.org/install.html">(Veja como você instala o Maven)</a>

**Recomendações**
- É recomendável que você tenha instalado o Google Chrome ou Edge
- Eu recomendo usar o Eclipse como IDE de desenvolvimento

**A instalação e inicialização são 4 etapas!**
1. Clone este repositório
2. Entre na pasta descompactada
3. Build com Maven
4. Rode o projeto com o Docker Compose

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

### 3. Build o projeto com Maven - (Pasta raiz)
```
mvn clean install
```
- Observações:
    - mvn ➡️ Você está chamando o executável, o que significa que você precisa do Maven instalado em sua máquina.

    - clean ➡️ Excluirá todos os arquivos e recursos Java .class compilados anteriormente (como .properties) no projeto, sua compilação começará do zero.

    - install ➡️ Compilar, testar e empacotar seu projeto Java e até mesmo instalar/copiar seu arquivo .jar/.war construído em seu repositório Maven local. <a href="https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html"> (Veja o Ciclo de vida do Maven )</a>

---

### 4. Inicialize o projeto com o Docker Compose - (Pasta raiz)
```
docker-compose up
```
- Observações:
    - O Compose é uma ferramenta para definir e executar aplicativos Docker de vários contêineres. Com o Compose, você usa um arquivo YAML para configurar os serviços do seu aplicativo. Então, com um único comando, você cria e inicia todos os serviços da sua configuração. <a href="https://docs.docker.com/compose/reference/up/">(Veja sobre docker-compose up)</a> 



<br>

## 🎮Getting Started
- Abra o navegador e entre no seguinte link: http://localhost:8080
    - O link acima irá abrir o Swagger do projeto em questão!

<br>

## 📁PostgreSQL
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
