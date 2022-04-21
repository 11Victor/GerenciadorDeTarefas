package br.com.manager.tasks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.manager.tasks.model.Pessoa;
import br.com.manager.tasks.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	// Cadastrar nova pessoa
	public Optional<Pessoa> postPessoa(@RequestBody Pessoa pessoa) {
		return Optional.of(pessoaRepository.save(pessoa));
	}

	// Editar pessoa
	public Optional<Pessoa> putPessoa(@RequestBody Pessoa pessoa) {
		return Optional.of(pessoaRepository.save(pessoa));
	}

	// Buscar todas pessoas
	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	// Buscar por ID
	public Optional<Pessoa> findById(long id) {
		return pessoaRepository.findById(id);
	}

}
