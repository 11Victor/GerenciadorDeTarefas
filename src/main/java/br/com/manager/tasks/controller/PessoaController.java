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

import br.com.manager.tasks.model.Pessoa;
import br.com.manager.tasks.repository.PessoaRepository;
import br.com.manager.tasks.service.PessoaService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	// Cadastrar Pessoa
	@PostMapping("/post/pessoas")
	public ResponseEntity<Pessoa> postPessoa(@RequestBody Pessoa pessoa) {
		return pessoaService.postPessoa(pessoa).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Editar Pessoa
	@PutMapping("/put/pessoas/{id}")
	public ResponseEntity<Pessoa> putPessoa(@RequestBody Pessoa pessoa) {
		return pessoaService.putPessoa(pessoa).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Deletar Pessoa
	@DeleteMapping("/delete/pessoas/{id}")
	public void deletePessoa(@PathVariable long id) {
		pessoaRepository.deleteById(id);
	}
	
	// Buscar todas pessoas
	@GetMapping("/get/pessoas/todas")
	public ResponseEntity<List<Pessoa>> getAll() {
		return ResponseEntity.ok(pessoaService.findAll());
	}

	// Buscar Pessoa por Id
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getById(@PathVariable long id) {
		return pessoaService.findById(id).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

}
