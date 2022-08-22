package br.com.manager.tasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.manager.tasks.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM tb_user u WHERE u.email = ?1 AND u.enable = true", nativeQuery = true)
	User findByUsername(String username);

	@Query(value = "SELECT * FROM tb_user u WHERE u.role = 'manager' AND u.enable = true", nativeQuery = true)
	List<User> findAllManagers();

	List<User> findByManagerId(Long managerId);
}