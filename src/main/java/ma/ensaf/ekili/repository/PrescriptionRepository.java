package ma.ensaf.ekili.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ma.ensaf.ekili.domain.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

	Page<Prescription> findByDeletedFalse(Pageable pageable);
}
