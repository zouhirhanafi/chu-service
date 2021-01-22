package ma.ensaf.ekili.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

/**
 * A ExamenClinique.
 */
@Getter
@Setter

@Entity
@Table(name = "examen_clinique")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamenClinique extends AbstractPersistable<Long> {
	private Double gcs;

	private String pa;

	private Integer diurese;

	private String autre;

}
