package ma.ensaf.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.ensaf.user.domain.User;
import ma.ensaf.user.dto.UserDto;
import ma.ensaf.user.dto.UserMapper;
import ma.ensaf.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserMapper userMapper;
	
	private String hashPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	public User create(UserDto userDto) {
		User user = userMapper.fromDto(userDto);
		user.setPassword(hashPassword(user.getPassword()));
		user = userRepository.save(user);
		
		//TODO send mail validation
		return user;
	}
}
