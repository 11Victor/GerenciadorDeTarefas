package br.com.manager.tasks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.manager.tasks.model.Pessoa;
import br.com.manager.tasks.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	// Buscar todas pessoas
	public List<Pessoa> BuscarPessoas() {
		return pessoaRepository.findAll();
	}

}
