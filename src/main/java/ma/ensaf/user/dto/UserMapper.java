package ma.ensaf.user.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.ensaf.user.domain.User;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;

//	public PrescriptionDto convertToDto(Prescription entity) {
//		PrescriptionDto dto = modelMapper.map(entity, PrescriptionDto.class);
//		return dto;
//	}

	public User fromDto(UserDto dto) {
		User object = modelMapper.map(dto, User.class);
		return object;
	}
}
