package ma.ensaf.ekili.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.ensaf.ekili.domain.Prescription;
import ma.ensaf.ekili.dto.PrescriptionDto;
import ma.ensaf.ekili.dto.PrescriptionMapper;
import ma.ensaf.ekili.repository.PrescriptionRepository;

@Service
public class PrescriptionService {

	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	@Autowired
	private PrescriptionMapper prescriptionMapper;

	public Prescription create(Prescription entity) {
//		throw new BadRequestException();
		return prescriptionRepository.save(entity);
	}

	public Prescription update(Prescription entity) {
		return prescriptionRepository.save(entity);
	}
	
	public Page<PrescriptionDto> findAll(Pageable pageable) {
//		throw new NotFoundException();
		Page<Prescription> prescriptions = prescriptionRepository.findByDeletedFalse(pageable);
//		List<PrescriptionDto> content = new ArrayList<PrescriptionDto>(prescriptions.getContent().size());
//		for (Prescription prescription : prescriptions.getContent()) {
//			content.add(prescriptionMapper.convertToDto(prescription));
//		}
		List<PrescriptionDto> content = prescriptions.getContent().stream()
				.map(prescription -> {
					return prescriptionMapper.convertToDto(prescription);
				})
//				.map(prescription -> prescriptionMapper.convertToDto(prescription))
//				.map(prescriptionMapper::convertToDto)
				.collect(Collectors.toList());
		
		Page<PrescriptionDto> dtos = new PageImpl<PrescriptionDto>(content, pageable, prescriptions.getTotalElements());
		return dtos;
	}

	public Optional<Prescription> findById(Long id) {
		return prescriptionRepository.findById(id).filter(p -> !p.getDeleted());
	}

	/**
	 * suppression logique d'une prescription
	 * 
	 * @param id
	 */
	@Transactional
	public void logicDeleteById(Long id) {
		findById(id).ifPresent(prescription -> {
			prescription.setDeleted(true);
//			update(prescription);
		});
	}

	@Transactional()
	public void deleteById(Long id) {
		try {
			prescriptionRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			logicDeleteById(id);
		} catch (Exception ex) {
			System.out.println("exception : " + ex.getMessage());
			ex.printStackTrace();
			logicDeleteById(id);
		}
	}
	
}
