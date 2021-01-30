package ma.ensaf.ekili.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.ensaf.ekili.domain.DossierPatient;
import ma.ensaf.ekili.repository.DossierPatientRepository;
import ma.ensaf.support.exception.BadRequestException;

//@Slf4j
@Service
public class DossierPatientService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DossierPatientRepository dossierPatientRepository;

	public <S extends DossierPatient> S save(S entity) {
//		long currentTimeMillis = System.currentTimeMillis();
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//		}
//		log.trace("Création du dossier patient {} ...", entity.log());
		entity = dossierPatientRepository.save(entity);
//		long time = System.currentTimeMillis() - currentTimeMillis;
//		log.trace("Dossier patient créé avec succès {} dans {} ms", entity.getId(), time);
//		if (time > 5000) {
//			log.error("Création du dossier patient a pris plus de 5s ({})", time);
//		} else if (time > 2000) {
//			log.warn("Création du dossier patient a pris entre 2 et 5s ({})", time);
//		}
		return entity;
	}

	public <S extends DossierPatient> S update(S entity) {
		if (entity.getId() == null) {
			throw new BadRequestException("Dossier doit avec un id");
		}
//		try {
			entity = dossierPatientRepository.save(entity);
//		} catch (Exception e) {
//			log.error("Erreur maj dossier patient {}", entity, e);
//		}
		return entity;
	}

	public Page<DossierPatient> findAll(Pageable pageable) {
//		throw new BadRequestException("ip.unique");
		return dossierPatientRepository.findAll(pageable);
	}

	public Optional<DossierPatient> findById(Long id) {
//		throw new NotFoundException();
		Optional<DossierPatient> entity = dossierPatientRepository.findById(id);
		return entity;
	}

	public void deleteById(Long id) {
		dossierPatientRepository.deleteById(id);
	}
	
}
