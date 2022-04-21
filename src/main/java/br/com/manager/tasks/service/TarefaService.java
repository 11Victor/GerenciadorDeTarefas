package br.com.manager.tasks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.manager.tasks.model.Tarefa;
import br.com.manager.tasks.repository.TarefaRepository;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	// Cadastrar nova tarefa
	public Optional<Tarefa> postTarefa(@RequestBody Tarefa tarefa) {
		return Optional.of(tarefaRepository.save(tarefa));
	}

	// Editar pessoa
	public Optional<Tarefa> putTarefa(@RequestBody Tarefa tarefa) {
		return Optional.of(tarefaRepository.save(tarefa));
	}

	// Buscar todas tarefas
	public List<Tarefa> findAll() {
		return tarefaRepository.findAll();
	}

}
