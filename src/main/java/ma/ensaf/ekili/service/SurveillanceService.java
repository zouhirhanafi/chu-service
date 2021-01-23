package ma.ensaf.ekili.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import ma.ensaf.ekili.domain.Surveillance;
import ma.ensaf.ekili.domain.enumeration.StatutPrescription;
import ma.ensaf.ekili.repository.SurveillanceRepository;
import ma.ensaf.support.exception.BadRequestException;
import ma.ensaf.support.exception.NotFoundException;

@Service
public class SurveillanceService {

	@Autowired
	private SurveillanceRepository surveillanceRepository;
	@Autowired
	private PrescriptionService prescriptionService;

	@Transactional
	public Surveillance create(Surveillance entity) {
		return prescriptionService.findById(entity.getPrescription().getId()).map(prescription -> {
			int count = surveillanceRepository.findCountSurveillanceEncours(prescription.getPatient().getId());
			if (count > 0) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "surveillance.encours.existe");
			}
			prescription.setStatut(StatutPrescription.ENCOURS);
			return surveillanceRepository.save(entity);
		}).orElseThrow(() -> new BadRequestException("prescription.not_found"));	
	}

	public Surveillance update(Surveillance entity) {
		return surveillanceRepository.save(entity);
	}
	
	public Page<Surveillance> findAll(Pageable pageable) {
		return surveillanceRepository.findAll(pageable);
	}

	public Optional<Surveillance> findById(Long id) {
		return surveillanceRepository.findById(id);
	}

	/**
	 * suppression logique d'une prescription
	 * 
	 * @param id
	 */
	public void logicDeleteById(Long id) {
		findById(id).ifPresent(prescription -> {
			prescription.setDeleted(true);
		});
	}
	
	public void deleteById(Long id) {
		surveillanceRepository.deleteById(id);
	}

	public void updateStatus(Long id, StatutPrescription statut) {
		findById(id).map(surveillance -> {
			surveillance.getPrescription().setStatut(statut);
			return surveillance;
		}).orElseThrow(NotFoundException::new);
	}

	@Transactional
	public void close(Long id) {
		updateStatus(id, StatutPrescription.TERMINEE);
	}
	
	@Transactional
	public void cancel(Long id) {
		updateStatus(id, StatutPrescription.ANNULEE);
	}
	
}
