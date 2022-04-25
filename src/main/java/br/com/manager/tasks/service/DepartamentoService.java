package br.com.manager.tasks.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import br.com.manager.tasks.dto.DepartamentoCountDTO;
import br.com.manager.tasks.dto.DepartamentoDTO;
import br.com.manager.tasks.model.Departamento;
import br.com.manager.tasks.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

	// Cadastrar novo departamento
	public Optional<Departamento> postDepartamento(@RequestBody DepartamentoDTO dto) {
		if(departamentoRepository.findAllByTitulo(dto.getTitulo()).isEmpty()) {
			Departamento departamento = new Departamento(dto.getTitulo());
			return Optional.of(departamentoRepository.save(departamento));	
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados incorretos ou departamento já existente!!!!", null);
		}
	}

	// Editar pessoa
	public Optional<Departamento> putDepartamento(@RequestBody DepartamentoDTO dto, long id) {
		if(departamentoRepository.findById(id).isPresent()) {
			Departamento departamento = departamentoRepository.getById(id);
			departamento.setTitulo(dto.getTitulo());
			return Optional.of(departamentoRepository.save(departamento));
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados incorretos!!!!", null);
		}
	}
	
	// Listar departamento e quantidade de pessoas e tarefas
	public List<DepartamentoCountDTO> listarDepartamentos() {
		List<DepartamentoCountDTO> DepartamentoCountDTO = departamentoRepository.findAll().stream().map(x -> new DepartamentoCountDTO(x))
				.collect(Collectors.toList());
		return DepartamentoCountDTO;
	}
	
	// Buscar todas pessoas
	public List<Departamento> findAll() {
		return departamentoRepository.findAll();
	}

}
