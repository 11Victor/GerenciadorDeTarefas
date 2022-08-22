package br.com.manager.tasks.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.manager.tasks.dto.user.UserDTO;
import br.com.manager.tasks.dto.user.UserPickerDTO;
import br.com.manager.tasks.dto.user.UserResDTO;
import br.com.manager.tasks.exceptions.ItemNotFoundException;
import br.com.manager.tasks.security.model.ResponseMessage;
import br.com.manager.tasks.service.UserService;

@RestController
@RequestMapping(value = "/users")
@Validated
public class UserController {
	@Autowired
	private UserService userService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
	@GetMapping("")
	public ResponseEntity<ResponseMessage> findAll(Pageable pageable) {
		Page<UserResDTO> users = userService.findAll(pageable);

		return ResponseEntity.ok().body(ResponseMessage.success(HttpStatus.OK, users));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
	@GetMapping("/picker")
	public ResponseEntity<ResponseMessage> findToPicker() {
		List<UserPickerDTO> users = userService.findToPicker();

		return ResponseEntity.ok().body(ResponseMessage.success(HttpStatus.OK, users));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
	@GetMapping("/{id}")
	public ResponseEntity<ResponseMessage> findById(@PathVariable(value = "id") Long id) {
		try {
			UserResDTO user = userService.findById(id);
			return ResponseEntity.ok().body(ResponseMessage.success(HttpStatus.OK, user));
		} catch (ItemNotFoundException err) {
			return ResponseEntity.status(404).body(ResponseMessage.error(HttpStatus.NOT_FOUND, err.getMessage()));
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MANAGER')")
	@GetMapping("/me")
	public ResponseEntity<ResponseMessage> getMe() {
		try {
			UserResDTO user = userService.getMe();
			return ResponseEntity.ok().body(ResponseMessage.success(HttpStatus.OK, user));
		} catch (ItemNotFoundException err) {
			return ResponseEntity.status(404).body(ResponseMessage.error(HttpStatus.NOT_FOUND, err.getMessage()));
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
	@PostMapping("")
	public ResponseEntity<ResponseMessage> save(@Valid @RequestBody UserDTO userDTO) {
		try {
			UserResDTO user = userService.save(userDTO);
			return ResponseEntity.status(201).body(ResponseMessage.success(HttpStatus.CREATED, user));
		} catch (ItemNotFoundException err) {
			err.printStackTrace();
			return ResponseEntity.status(400).body(ResponseMessage.error(HttpStatus.BAD_REQUEST, err.getMessage()));
		} catch (MessagingException err) {
			err.printStackTrace();
			return ResponseEntity.ok().body(ResponseMessage.success(HttpStatus.OK, err.getMessage()));
		} catch (RuntimeException err) {
			err.printStackTrace();
			return ResponseEntity.status(400).body(ResponseMessage.error(HttpStatus.BAD_REQUEST, err.getMessage()));
		} catch (Exception err) {
			err.printStackTrace();
			return ResponseEntity.status(500).body(ResponseMessage.error(HttpStatus.BAD_REQUEST, err.getMessage()));
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
	@PutMapping("/{id}")
	public ResponseEntity<ResponseMessage> update(@PathVariable(value = "id") Long id,
			@Valid @RequestBody UserDTO userDTO) {
		try {
			UserResDTO user = userService.update(id, userDTO);
			return ResponseEntity.ok().body(ResponseMessage.success(HttpStatus.OK, user));
		} catch (DataIntegrityViolationException err) {
			err.printStackTrace();
			return ResponseEntity.status(400).body(ResponseMessage.error(HttpStatus.BAD_REQUEST,
					"Não foi possível atualizar o usuário com as informações fornecidas, verifique se já existe algum usuário com o CPF e/ou e-mail fornecidos"));
		} catch (ItemNotFoundException err) {
			err.printStackTrace();
			return ResponseEntity.status(400).body(ResponseMessage.error(HttpStatus.BAD_REQUEST, err.getMessage()));
		} catch (RuntimeException err) {
			err.printStackTrace();
			return ResponseEntity.status(400).body(ResponseMessage.error(HttpStatus.BAD_REQUEST, err.getMessage()));
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MANAGER')")
	@PutMapping("")
	public ResponseEntity<ResponseMessage> update(@RequestBody UserDTO userDTO) {
		try {
			UserResDTO user = userService.update(userDTO);
			return ResponseEntity.ok().body(ResponseMessage.success(HttpStatus.OK, user));
		} catch (ItemNotFoundException err) {
			err.printStackTrace();
			return ResponseEntity.status(400).body(ResponseMessage.error(HttpStatus.BAD_REQUEST, err.getMessage()));
		} catch (RuntimeException err) {
			err.printStackTrace();
			return ResponseEntity.status(400).body(ResponseMessage.error(HttpStatus.BAD_REQUEST, err.getMessage()));
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseMessage> delete(@PathVariable(value = "id") Long id) {
		try {
			userService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (ItemNotFoundException err) {
			return ResponseEntity.status(404).body(ResponseMessage.error(HttpStatus.NOT_FOUND, err.getMessage()));
		} catch (RuntimeException err) {
			return ResponseEntity.status(400).body(ResponseMessage.error(HttpStatus.BAD_REQUEST, err.getMessage()));
		}
	}

	@GetMapping("/resetpassword")
	public ResponseEntity<ResponseMessage> resetUserPassword(@RequestParam(value = "email") String email) {
		try {
			userService.resetPassword(email);
			return ResponseEntity.ok().body(ResponseMessage.success(HttpStatus.OK,
					"E-mail enviado ao usuário informado, verifique em sua caixa de entrada e/ou spam."));
		} catch (MessagingException err) {
			err.printStackTrace();
			return ResponseEntity.status(400).body(ResponseMessage.error(HttpStatus.BAD_REQUEST, err.getMessage()));
		} catch (ItemNotFoundException err) {
			err.printStackTrace();
			return ResponseEntity.status(400).body(ResponseMessage.error(HttpStatus.BAD_REQUEST, err.getMessage()));
		} catch (Exception err) {
			err.printStackTrace();
			return ResponseEntity.status(500).body(ResponseMessage.error(HttpStatus.BAD_REQUEST, err.getMessage()));
		}
	}
}
