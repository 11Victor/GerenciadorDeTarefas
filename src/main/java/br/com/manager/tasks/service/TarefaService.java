package br.com.manager.tasks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import br.com.manager.tasks.dto.TarefaDTO;
import br.com.manager.tasks.model.Departamento;
import br.com.manager.tasks.model.Tarefa;
import br.com.manager.tasks.repository.DepartamentoRepository;
import br.com.manager.tasks.repository.TarefaRepository;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	DepartamentoRepository departamentoRepository;

	// Cadastrar nova tarefa
	public Optional<Tarefa> postTarefa(@RequestBody TarefaDTO dto) {
		if (tarefaRepository.findAllByTitulo(dto.getTitulo()).isEmpty()
				&& departamentoRepository.findById(dto.getIdDepartamento()).isPresent()) {
			Departamento departamento = new Departamento();
			departamento.setId(dto.getIdDepartamento());
			Tarefa tarefa = new Tarefa(dto.getTitulo(), dto.getDescricao(), dto.getPrazo(), dto.getDuracao(),
					departamento);
			return Optional.of(tarefaRepository.save(tarefa));
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Possiveis erros: Nome da Tarefa já existente ou Departamento não localizado!!!!", null);
		}
	}

	// Editar tarefa
	public Optional<Tarefa> putTarefa(@RequestBody TarefaDTO dto, long id) {
		if (tarefaRepository.findById(id).isPresent()
				&& departamentoRepository.findById(dto.getIdDepartamento()).isPresent()) {
			Tarefa tarefa = tarefaRepository.getById(id);
			Departamento departamento = new Departamento();
			tarefa.setTitulo(dto.getTitulo());
			tarefa.setDescricao(dto.getDescricao());
			tarefa.setPrazo(dto.getPrazo());
			tarefa.setDuracao(dto.getDuracao());
			departamento.setId(dto.getIdDepartamento());
			tarefa.setIdDepartamento(departamento);
			return Optional.of(tarefaRepository.save(tarefa));
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Possiveis erros: Nome da Tarefa já existente ou Departamento não localizado!!!!", null);
		}
	}

	// Buscar todas tarefas
	public List<Tarefa> findAll() {
		return tarefaRepository.findAll();
	}

}
