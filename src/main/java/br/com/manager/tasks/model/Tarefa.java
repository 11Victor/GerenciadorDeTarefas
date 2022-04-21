package br.com.manager.tasks.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TB_TAREFAS")
public class Tarefa {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String titulo;
	
	private String descricao;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate prazo;
	
	private int duracao;
	
	private Boolean finalizado;
	
	
	public Tarefa(long id, String titulo, String descricao, LocalDate prazo, int duracao, Boolean finalizado) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.prazo = prazo;
		this.duracao = duracao;
		this.finalizado = finalizado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	
	
	
	
	
}
