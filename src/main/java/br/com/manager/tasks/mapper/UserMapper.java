package br.com.manager.tasks.mapper;

import org.modelmapper.ModelMapper;

import br.com.manager.tasks.dto.user.UserResDTO;
import br.com.manager.tasks.model.User;

public class UserMapper {
	private final ModelMapper modelMapper = new ModelMapper();

	public UserResDTO toDto(User obj) {
		return modelMapper.map(obj, UserResDTO.class);
	}

}
