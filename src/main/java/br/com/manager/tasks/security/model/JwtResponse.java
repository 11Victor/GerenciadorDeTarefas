package br.com.manager.tasks.security.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class JwtResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private final Long id;
	private final String name;
	private final String email;
	private final String token;
	private final String role;
}