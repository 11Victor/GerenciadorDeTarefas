package br.com.manager.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.manager.tasks.model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{
	
	
	
}
