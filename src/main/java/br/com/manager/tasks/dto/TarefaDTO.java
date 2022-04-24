package br.com.manager.tasks.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.manager.tasks.model.Tarefa;

public class TarefaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String titulo;
	
	@NotNull
	private String descricao;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate prazo;
	
	@NotBlank
	private int duracao;
	
	@NotBlank
	private long idDepartamento;
	
	public TarefaDTO(Tarefa tarefa) {
		super();
		this.titulo = tarefa.getTitulo();
		this.descricao = tarefa.getDescricao();
		this.prazo = tarefa.getPrazo();
		this.duracao = tarefa.getDuracao();
		this.idDepartamento = tarefa.getIdDepartamento().getId();
	}

	public TarefaDTO() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

}
