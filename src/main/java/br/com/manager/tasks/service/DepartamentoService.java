package br.com.manager.tasks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.manager.tasks.model.Departamento;
import br.com.manager.tasks.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

	// Cadastrar novo departamento
	public Optional<Departamento> postDepartamento(@RequestBody Departamento departamento) {
		return Optional.of(departamentoRepository.save(departamento));
	}

	// Editar pessoa
	public Optional<Departamento> putDepartamento(@RequestBody Departamento departamento) {
		return Optional.of(departamentoRepository.save(departamento));
	}

	// Buscar todas pessoas
	public List<Departamento> findAll() {
		return departamentoRepository.findAll();
	}

}
