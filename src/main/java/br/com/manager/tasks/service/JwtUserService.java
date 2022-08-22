package br.com.manager.tasks.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.manager.tasks.model.User;
import br.com.manager.tasks.repository.UserRepository;

@Service
public class JwtUserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);

		if (user != null && username.equals(user.getEmail())) {
			return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
					getAuthority(user));
		} else {
			throw new BadCredentialsException("Usuário e/ou senha inválidos");
		}
	}

	public void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new DisabledException("Usuário desabilitado", e);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Usuário e/ou senha inválidos", e);
		}
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase().trim()));
		return authorities;
	}
}
