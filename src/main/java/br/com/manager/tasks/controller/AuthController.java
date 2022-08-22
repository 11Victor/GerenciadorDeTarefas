package br.com.manager.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.manager.tasks.dto.user.UserResDTO;
import br.com.manager.tasks.security.JwtTokenUtil;
import br.com.manager.tasks.security.model.JwtRequest;
import br.com.manager.tasks.security.model.JwtResponse;
import br.com.manager.tasks.security.model.ResponseMessage;
import br.com.manager.tasks.service.JwtUserService;
import br.com.manager.tasks.service.UserService;

@RestController
@RequestMapping("/login")
public class AuthController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUserService userDetailsService;

	@PostMapping("")
	public ResponseEntity<ResponseMessage> login(@RequestBody JwtRequest body) throws Exception {
		try {
			UserResDTO user = userService.findByUsername(body.getUsername());

			userDetailsService.authenticate(body.getUsername(), body.getPassword());

			UserDetails userDetails = userDetailsService.loadUserByUsername(body.getUsername());

			String token = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok().body(ResponseMessage.success(HttpStatus.OK,
					new JwtResponse(user.getId(), user.getName(), userDetails.getUsername(), token, user.getRole())));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400)
					.body(ResponseMessage.error(HttpStatus.BAD_REQUEST, "Usuário e/ou senha inválidos"));
		}
	}
}
