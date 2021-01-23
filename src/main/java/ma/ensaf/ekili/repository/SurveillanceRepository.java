package ma.ensaf.ekili.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.ensaf.ekili.domain.Surveillance;

public interface SurveillanceRepository extends JpaRepository<Surveillance, Long> {
	
	@Query("select count(s) from Surveillance s where s.prescription.patient.id = :id "
			+ "and s.prescription.statut in ('AVENIR', 'ENCOURS')")
	int findCountSurveillanceEncours(@Param("id") Long id);

}
