package br.com.manager.tasks.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import br.com.manager.tasks.dto.PessoaDTO;
import br.com.manager.tasks.dto.PessoaHorasDTO;
import br.com.manager.tasks.dto.PessoaMediaDTO;
import br.com.manager.tasks.dto.PessoaMediaRetornoDTO;
import br.com.manager.tasks.model.Departamento;
import br.com.manager.tasks.model.Pessoa;
import br.com.manager.tasks.repository.DepartamentoRepository;
import br.com.manager.tasks.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

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

	// Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas
	public List<PessoaHorasDTO> listarPessoas() {
		List<PessoaHorasDTO> PessoaHorasDTO = pessoaRepository.findAll().stream().map(x -> new PessoaHorasDTO(x))
				.collect(Collectors.toList());
		return PessoaHorasDTO;
	}

	// Buscar pessoas por nome e retorna média de horas gastas por tarefa
	public List<PessoaMediaRetornoDTO> buscarPessoaMediaHoras(PessoaMediaDTO pessoaEntrada) {
		List<PessoaMediaRetornoDTO> PessoaMediaRetornoDTO = pessoaRepository
				.findAllByNomeContainingIgnoreCase(pessoaEntrada.getNome()).stream()
				.map(x -> new PessoaMediaRetornoDTO(x)).collect(Collectors.toList());
		return PessoaMediaRetornoDTO;
	}

}
