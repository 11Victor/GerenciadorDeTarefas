package br.com.manager.tasks.dto;

import java.io.Serializable;
import java.util.stream.Collectors;

import br.com.manager.tasks.model.Pessoa;
import br.com.manager.tasks.model.Tarefa;

public class PessoaHorasDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;

	private String departamento;

	private int horasTarefas;

	public PessoaHorasDTO(Pessoa pessoa) {
		super();
		this.nome = pessoa.getNome();
		this.departamento = pessoa.getIdDepartamento().getTitulo();
		this.horasTarefas = pessoa.getTarefa().stream().collect(Collectors.summingInt(Tarefa::getDuracao));
	}

	public PessoaHorasDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public int getHorasTarefas() {
		return horasTarefas;
	}

	public void setHorasTarefas(int horasTarefas) {
		this.horasTarefas = horasTarefas;
	}

}
