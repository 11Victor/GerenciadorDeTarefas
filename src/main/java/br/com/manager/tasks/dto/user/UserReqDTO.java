package br.com.manager.tasks.dto.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserReqDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	private String password;
}
