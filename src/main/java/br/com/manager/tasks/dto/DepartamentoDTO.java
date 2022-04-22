package br.com.manager.tasks.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.manager.tasks.model.Departamento;

public class DepartamentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String titulo;
	
	public DepartamentoDTO(Departamento departamento) {
		super();
		this.titulo = departamento.getTitulo();
	}

	public DepartamentoDTO() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
}
