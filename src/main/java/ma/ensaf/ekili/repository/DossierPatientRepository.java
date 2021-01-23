package ma.ensaf.ekili.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.ensaf.ekili.domain.DossierPatient;

public interface DossierPatientRepository extends JpaRepository<DossierPatient, Long> {

}
