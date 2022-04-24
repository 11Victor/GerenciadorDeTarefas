package br.com.manager.tasks.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "TB_TAREFAS")
public class Tarefa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String titulo;
	
	private String descricao;
	
	private LocalDate prazo;
	
	private int duracao;
	
	private Boolean finalizado=false;
	
	@ManyToOne
	@JsonIgnoreProperties({"tarefa", "idDepartamento"})
	@JoinColumn(name = "pessoa")
	private Pessoa pessoa;
	
	@ManyToOne
	@JsonIgnoreProperties({"tarefa", "pessoa"})
	@JoinColumn(name = "idDepartamento")
	private Departamento idDepartamento;
	
	public Tarefa(String titulo, String descricao, LocalDate prazo, int duracao, Departamento departamento) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.prazo = prazo;
		this.duracao = duracao;
		this.idDepartamento = departamento;
	}
	
	public Tarefa() {
		
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Departamento getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Departamento idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	
	
}
