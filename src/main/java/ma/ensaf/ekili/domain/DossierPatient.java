package ma.ensaf.ekili.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

/**
 * A DossierPatient.
 */
@Getter
@Setter

@Entity
@Table(name = "dossier_patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DossierPatient extends AbstractPersistable<Long> {

	@NotNull
	@Column(name = "ip", nullable = false, unique = true)
	private Long ip;

	@NotNull
	@Column(nullable = false)
	private String nom;

	@NotNull
	@Column(nullable = false)
	private String prenom;

	@NotNull
	@Column(nullable = false)
	private Integer genre;

	private String tel;

	private String adresse;

	private Integer amo;

	@Column(name = "type_centre_origine")
	private Integer typeCentreOrigine;

	@Column(name = "ville_centre_origine")
	private Integer villeCentreOrigine;

	private String observation;

	private LocalDate naissance;

	@OneToOne
	@JoinColumn(unique = true)
	private Antecedent antecedent;

	@OneToOne
	@JoinColumn(unique = true)
	private Diagnostic diagnostic;

	@OneToOne
	@JoinColumn(unique = true)
	private IndicationHd indicationHd;

	@OneToOne
	@JoinColumn(unique = true)
	private ExamenClinique examenClinique;

}
