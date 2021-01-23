package ma.ensaf.ekili.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.ensaf.ekili.domain.Prescription;

@Component
public class PrescriptionMapper {

	@Autowired
	private ModelMapper modelMapper;

	public PrescriptionDto convertToDto(Prescription entity) {
		PrescriptionDto dto = modelMapper.map(entity, PrescriptionDto.class);
		return dto;
	}

	public Prescription fromDto(PrescriptionDto dto) {
		Prescription entity = modelMapper.map(dto, Prescription.class);
		return entity;
	}
}
