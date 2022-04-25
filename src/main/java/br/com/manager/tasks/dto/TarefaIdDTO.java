package br.com.manager.tasks.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.manager.tasks.model.Tarefa;

public class TarefaIdDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private long idTarefa;
	
	public TarefaIdDTO(Tarefa tarefa) {
		super();
		this.idTarefa = tarefa.getId();
	}

	public TarefaIdDTO() {
		super();
	}

	public long getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(long idTarefa) {
		this.idTarefa = idTarefa;
	}
	
}
