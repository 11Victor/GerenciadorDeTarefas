package br.com.manager.tasks.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.manager.tasks.dto.user.UserDTO;
import br.com.manager.tasks.dto.user.UserPickerDTO;
import br.com.manager.tasks.dto.user.UserResDTO;
import br.com.manager.tasks.exceptions.ItemNotFoundException;
import br.com.manager.tasks.mapper.UserMapper;
import br.com.manager.tasks.model.Mail;
import br.com.manager.tasks.model.User;
import br.com.manager.tasks.repository.UserRepository;
import br.com.manager.tasks.security.util.PasswordHelper;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	private String password;

	public Page<UserResDTO> findAll(Pageable pageable) {
		Page<User> page = userRepository.findAll(pageable);
		long totalElements = page.getTotalElements();

		return new PageImpl<UserResDTO>(
				page.stream().filter(User::isEnable).map(u -> new UserMapper().toDto(u)).collect(Collectors.toList()),
				pageable, totalElements);
	}

	public List<UserPickerDTO> findToPicker() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) authentication
				.getPrincipal();

		User user = userRepository.findByUsername(springUser.getUsername());

		List<User> users;

		if (user == null)
			throw new ItemNotFoundException("Usuário não encontrado na base de dados");

		if (user.getRole().equals("manager")) {
			users = userRepository.findByManagerId(user.getId());
		} else {
			users = userRepository.findAll();
		}

		return users.stream().map(UserPickerDTO::new).collect(Collectors.toList());
	}

	public List<UserResDTO> findAllManager() {
		List<User> result = userRepository.findAllManagers();

		return result.stream().map(u -> new UserMapper().toDto(u)).collect(Collectors.toList());
	}

	public UserResDTO findById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException("Usuário não encontrado na base de dados"));

		return new UserMapper().toDto(user);
	}

	public UserResDTO getMe() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) authentication
				.getPrincipal();

		User user = userRepository.findByUsername(springUser.getUsername());

		return new UserMapper().toDto(user);
	}

	public UserResDTO findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new ItemNotFoundException("Usuário não encontrado na base de dados");
		}

		return new UserMapper().toDto(user);
	}

	public UserResDTO save(UserDTO dto) throws Exception {
		User userByEmail = userRepository.findByUsername(dto.getEmail());

		if (userByEmail != null)
			throw new RuntimeException("Usuário já cadastrado");

		User user = fromData(null, dto);

		System.out.println("Username: " + dto.getEmail() + "\nPassword: " + this.password);

		String emailContent = "<p>Sua conta acabou de ser criada no sistema,</p>"
				+ "<p>Para acessa-la utilize o seu e-mail com a senha: <strong>" + this.password
				+ "</strong></p>";
		Mail mailModel = new Mail(dto.getEmail(), dto.getName(), "Seja bem vindo ao GERENCIADOR DE TAREFAS!", emailContent);

		try {
			emailService.sendEmail(mailModel);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível criar o usuáro no momento, tente novamente mais tarde.");
		}

		user = userRepository.save(user);

		return new UserMapper().toDto(user);
	}

	public UserResDTO update(Long id, UserDTO dto) {
		User user = fromData(id, dto);

		user = userRepository.save(user);

		return new UserMapper().toDto(user);
	}

	public UserResDTO update(UserDTO dto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) authentication
				.getPrincipal();

		User user = userRepository.findByUsername(springUser.getUsername());
		if (user == null) {
			throw new ItemNotFoundException("Usuário não encontrado na base de dados");
		}

		if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
			user.setPassword(PasswordHelper.passwordEncrypted(dto.getPassword()));
		}

		if (dto.getName() != null && !dto.getName().isEmpty()) {
			user.setName(dto.getName());
		}

		user = userRepository.save(user);

		return new UserMapper().toDto(user);
	}

	public void deleteById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException("Usuário não encontrado na base de dados"));

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) authentication
				.getPrincipal();

		if (user.getEmail().equals(springUser.getUsername())) {
			throw new IllegalStateException("Não é possível desabilitar o seu próprio usuário");
		}

		userRepository.delete(user);
	}

	public void resetPassword(String email) throws Exception {
		User user = userRepository.findByUsername(email);

		if (user == null) {
			throw new ItemNotFoundException("Usuário não encontrado na base de dados");
		}

		String newPassword = PasswordHelper.passwordGenerate();

		String emailContent = "<p>Conforme pedido, segue a sua nova senha para acessar o sistema: <strong>"
				+ newPassword + "</strong></p>";
		Mail mailModel = new Mail(user.getEmail(), user.getName(), "Alteração de senha", emailContent);

		try {
			emailService.sendEmail(mailModel);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível trocar a senha do usuáro no momento, tente novamente mais tarde.");
		}

		user.setPassword(PasswordHelper.passwordEncrypted(newPassword));

		userRepository.save(user);
	}

	private User fromData(Long id, UserDTO dto) {
		User user = id == null ? new User()
				: userRepository.findById(id)
						.orElseThrow(() -> new ItemNotFoundException("Usuário não encontrado na base de dados"));

		User manager = dto.getManager() == null ? null
				: dto.getManager().getId() == null ? null
						: userRepository.findById(dto.getManager().getId())
								.orElseThrow(() -> new ItemNotFoundException("Gestor não encontrado na base de dados"));

		if (manager != null)
			if (manager.getEmail().equals(dto.getEmail()))
				throw new IllegalStateException("Gestor não pode ser o seu próprio gestor");

		String newPass;

		if (id == null) {
			this.password = PasswordHelper.passwordGenerate();
			newPass = PasswordHelper.passwordEncrypted(this.password);
		} else if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
			newPass = PasswordHelper.passwordEncrypted(dto.getPassword());
		} else {
			newPass = user.getPassword();
		}

		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(newPass);
		user.setRole(dto.getRole());
		user.setManager(manager);

		return user;
	}
}
