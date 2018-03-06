/*package com.example.demo.component;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
//import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Component
public class UserComponent {
	@Autowired
	UserRepository userRepository;
	ModelMapper mapper;
	UserDto userDto;

	public UserComponent() {
		// Default constructor
		mapper = new ModelMapper();
	}

/*public UserDto getUser(int id)
{
	//User user=userRepository.findOne(id);
	userDto = mapper.map(user, UserDto.class);
	return userDto;
}
}*/

