package br.com.manager.tasks.dto.user;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull(message = "Nome do usuário não informado")
	private String name;

	@Email
	@NotNull(message = "E-mail do usuário não informado")
	private String email;

	private String role;

}
