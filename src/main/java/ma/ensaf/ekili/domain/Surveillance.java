package ma.ensaf.ekili.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;
import ma.ensaf.support.domain.CustomAbstractPersistable;

/**
 * A Surveillance.
 */
@Getter
@Setter

@Entity
@Table(name = "surveillance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Surveillance extends CustomAbstractPersistable<Long> {

	@NotNull
	@Column(name = "infirmier", nullable = false)
	private Integer infirmier;

	@Column(name = "poste")
	private Integer poste;

	@Column(name = "generateur")
	private Integer generateur;

	@Column(name = "poid")
	private Double poid;

	@Column(name = "ufnet")
	private Double ufnet;

	@Column(name = "etat_conscience")
	private Integer etatConscience;

	@Column(name = "eupneique")
	private Integer eupneique;

	@Column(name = "restitution_par")
	private Integer restitutionPar;

	@Column(name = "autre_complication")
	private String autreComplication;

	@Column(name = "observation")
	private String observation;

	@OneToOne
	@JoinColumn(unique = true)
	private TraitementPerdialyse traitement;

	@NotNull
	@OneToOne
	@JoinColumn(unique = true)
	private Prescription prescription;
}
