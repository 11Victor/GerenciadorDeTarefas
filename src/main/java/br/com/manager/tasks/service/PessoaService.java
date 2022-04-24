package br.com.manager.tasks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import br.com.manager.tasks.dto.PessoaDTO;
import br.com.manager.tasks.model.Departamento;
import br.com.manager.tasks.model.Pessoa;
import br.com.manager.tasks.repository.DepartamentoRepository;
import br.com.manager.tasks.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	DepartamentoRepository departamentoRepository;

	// Cadastrar nova pessoa
	public Optional<Pessoa> postPessoa(@RequestBody PessoaDTO dto) {
		if (departamentoRepository.findById(dto.getIdDepartamento()).isPresent()) {
			Departamento departamento = new Departamento();
			departamento.setId(dto.getIdDepartamento());
			Pessoa pessoa = new Pessoa(dto.getNome(), departamento);
			return Optional.of(pessoaRepository.save(pessoa));
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Possiveis erros: Nome de Usuário já existente ou Departamento não localizado!!!!", null);
		}
	}

	// Editar pessoa
	public Optional<Pessoa> putPessoa(@RequestBody PessoaDTO dto, long id) {

		if (pessoaRepository.findById(id).isPresent()
				&& departamentoRepository.findById(dto.getIdDepartamento()).isPresent()) {
			Pessoa pessoa = pessoaRepository.getById(id);
			Departamento departamento = new Departamento();
			departamento.setId(dto.getIdDepartamento());
			pessoa.setNome(dto.getNome());
			pessoa.setIdDepartamento(departamento);

			return Optional.of(pessoaRepository.save(pessoa));

		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados incorretos!!!!", null);
		}
	}

	// Buscar todas pessoas
	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

}
