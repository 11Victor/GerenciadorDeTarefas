package br.com.manager.tasks.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PESSOAS")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.REMOVE)
//	@JsonIgnoreProperties("pessoa")
//	private List<Tarefa> tarefa;
//	
//	@ManyToOne
//	@JsonIgnoreProperties("pessoa")
//	private Departamento departamento;
	
	
	
	
	
}
