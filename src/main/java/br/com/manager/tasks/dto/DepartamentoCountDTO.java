package br.com.manager.tasks.dto;

import java.io.Serializable;
import java.util.stream.Collectors;

import br.com.manager.tasks.model.Departamento;

public class DepartamentoCountDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String titulo;

	private Long pessoas;

	private Long tarefas;

	public DepartamentoCountDTO(Departamento departamento) {
		super();
		this.titulo = departamento.getTitulo();
		this.pessoas = departamento.getPessoa().stream().collect(Collectors.counting());
		this.tarefas = departamento.getTarefa().stream().collect(Collectors.counting());
	}

	public DepartamentoCountDTO() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getPessoas() {
		return pessoas;
	}

	public void setPessoas(Long pessoas) {
		this.pessoas = pessoas;
	}

	public Long getTarefas() {
		return tarefas;
	}

	public void setTarefas(Long tarefas) {
		this.tarefas = tarefas;
	}

}
