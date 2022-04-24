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

import br.com.manager.tasks.dto.DepartamentoDTO;
import br.com.manager.tasks.model.Departamento;
import br.com.manager.tasks.repository.DepartamentoRepository;
import br.com.manager.tasks.service.DepartamentoService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	// Buscar todos departamento
	@GetMapping("/get/departamento/all")
	public ResponseEntity<List<Departamento>> getAll() {
		return ResponseEntity.ok(departamentoService.findAll());
	}

	// Cadastrar departamento
	@PostMapping("/post/departamento")
	public ResponseEntity<Departamento> postDepartamento(@RequestBody DepartamentoDTO dto) {
		return departamentoService.postDepartamento(dto)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Editar departamento
	@PutMapping("/put/departamento/{id}")
	public ResponseEntity<Departamento> putDepartamento(@RequestBody DepartamentoDTO dto,@PathVariable("id") long id) {
		return departamentoService.putDepartamento(dto, id)
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Deletar departamento
	@DeleteMapping("/delete/departamento/{id}")
	public void deleteDepartamento(@PathVariable("id") long id) {
		departamentoRepository.deleteById(id);
	}

}
