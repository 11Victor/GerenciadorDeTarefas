package br.com.manager.tasks.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class PessoaMediaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private String nome;

	public PessoaMediaDTO(@NotNull String nome) {
		super();
		this.nome = nome;

	}

	public PessoaMediaDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
