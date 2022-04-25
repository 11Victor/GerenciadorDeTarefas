<h1 align="center">
	<img src="https://cdn-icons-png.flaticon.com/512/410/410909.png"  alt="Logo"  width="100"><br><br>
    Gerenciamento de Tarefas
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
 <a href="#documentation">Documentation</a>
 
</p>

## 📌About

<div>
    <p align="center">
    API-REST de gerenciamento de tarefas com três tabelas(Pessoa, Tarefa e Departamento)
    </p>
</div>

## 🚀Features

- Criar, Editar e Apagar (Pessoa, Tarefa e Departamento)
- Alocar uma pessoa na tarefa que tenha o mesmo departamento (put/tarefas/alocar/{id})
- Finalizar a tarefa (put/tarefas/finalizar/{id})
- Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas.(get/pessoas)
- Buscar pessoas por nome e retorna média de horas gastas por tarefa. (get/pessoas/gastos)
- Listar departamento e quantidade de pessoas e tarefas (get/departamentos)

## 🌐Technologies

- Java 8
- JPA
- Maven
- Spring Boot
- Swagger
- PostgreSQL
- Postman

## 📃Documentation

Link Documentação Swagger: https://tasks-documentacao.netlify.app/
