package br.com.manager.tasks.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "TB_DEPARTAMENTO")
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String titulo;
	
	@OneToMany(mappedBy = "idDepartamento", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("idDepartamento")
	private List<Pessoa> pessoa;
	
	@OneToMany(mappedBy = "idDepartamento", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("idDepartamento")
	private List<Tarefa> tarefa;
	
	public Departamento(String titulo) {
		this.titulo = titulo;
	}
	
	public Departamento() {
		
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

	public List<Pessoa> getPessoa() {
		return pessoa;
	}

	public void setPessoa(List<Pessoa> pessoa) {
		this.pessoa = pessoa;
	}

	public List<Tarefa> getTarefa() {
		return tarefa;
	}

	public void setTarefa(List<Tarefa> tarefa) {
		this.tarefa = tarefa;
	}
	
}
