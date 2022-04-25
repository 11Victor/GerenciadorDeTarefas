package br.com.manager.tasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.manager.tasks.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	public List<Pessoa> findAllByNome(String nome);
	
	public List<Pessoa> findAllByNomeContainingIgnoreCase(String nome);
}
