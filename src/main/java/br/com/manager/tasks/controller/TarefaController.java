package br.com.manager.tasks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.manager.tasks.model.Tarefa;
import br.com.manager.tasks.repository.TarefaRepository;
import br.com.manager.tasks.service.TarefaService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;

	@Autowired
	private TarefaRepository tarefaRepository;

	// Buscar todas tarefas
	@GetMapping("/get/tarefas/todas")
	public ResponseEntity<List<Tarefa>> getAll() {
		return ResponseEntity.ok(tarefaService.findAll());
	}

	// Cadastrar tarefa
	@PostMapping("/post/tarefas")
	public ResponseEntity<Tarefa> postTarefa(@RequestBody Tarefa tarefa) {
		return tarefaService.postTarefa(tarefa).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Editar tarefa
	@PutMapping("/put/tarefa/{id}")
	public ResponseEntity<Tarefa> putTarefa(@RequestBody Tarefa tarefa) {
		return tarefaService.putTarefa(tarefa).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Deletar tarefa
	@DeleteMapping("/delete/tarefa/{id}")
	public void deleteTarefa(@PathVariable long id) {
		tarefaRepository.deleteById(id);
	}

}
