package ma.ensaf.ekili.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.ensaf.ekili.domain.DossierPatient;
import ma.ensaf.ekili.repository.DossierPatientRepository;
import ma.ensaf.support.exception.BadRequestException;
import ma.ensaf.support.exception.NotFoundException;

@Service
public class DossierPatientService {

	@Autowired
	private DossierPatientRepository dossierPatientRepository;

	public <S extends DossierPatient> S save(S entity) {
		return dossierPatientRepository.save(entity);
	}

	public <S extends DossierPatient> S update(S entity) {
		return dossierPatientRepository.save(entity);
	}
	
	public Page<DossierPatient> findAll(Pageable pageable) {
		throw new BadRequestException("ip.unique");
//		return dossierPatientRepository.findAll(pageable);
	}

	public Optional<DossierPatient> findById(Long id) {
		throw new NotFoundException();
//		Optional<DossierPatient> entity = dossierPatientRepository.findById(id);
//		return entity;
	}

	public void deleteById(Long id) {
		dossierPatientRepository.deleteById(id);
	}
	
}
