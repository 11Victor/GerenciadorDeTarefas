package br.com.manager.tasks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.manager.tasks.dto.user.PessoaDTO;
import br.com.manager.tasks.dto.user.PessoaHorasDTO;
import br.com.manager.tasks.dto.user.PessoaMediaDTO;
import br.com.manager.tasks.dto.user.PessoaMediaRetornoDTO;
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

	// Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas
	@GetMapping("/get/pessoas")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MANAGER')")
	public ResponseEntity<List<PessoaHorasDTO>> findAll() {
		return ResponseEntity.ok(pessoaService.listarPessoas());
	}

	// Buscar pessoas por nome e retorna média de horas gastas por tarefa.
	@GetMapping("/get/pessoas/gastos")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MANAGER')")
	public ResponseEntity<List<PessoaMediaRetornoDTO>> findByNomeMediaHoras(@RequestBody PessoaMediaDTO pessoaEntrada) {
		return ResponseEntity.ok(pessoaService.buscarPessoaMediaHoras(pessoaEntrada));
	}

	// Cadastrar Pessoa
	@PostMapping("/post/pessoas")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MANAGER')")
	public ResponseEntity<Pessoa> postPessoa(@RequestBody PessoaDTO dto) {
		return pessoaService.postPessoa(dto).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Editar Pessoa
	@PutMapping("/put/pessoas/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MANAGER')")
	public ResponseEntity<Pessoa> putPessoa(@RequestBody PessoaDTO dto, @PathVariable("id") long id) {
		return pessoaService.putPessoa(dto, id).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Deletar Pessoa
	@DeleteMapping("/delete/pessoas/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MANAGER')")
	public void deletePessoa(@PathVariable("id") long id) {
		pessoaRepository.deleteById(id);
	}

}
