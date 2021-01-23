package ma.ensaf.ekili.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import ma.ensaf.ekili.domain.enumeration.StatutPrescription;
import ma.ensaf.support.domain.CustomAbstractPersistable;

/**
 * A Prescription.
 */
@Getter
@Setter

@Entity
@Table(name = "prescription")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Prescription extends CustomAbstractPersistable<Long> {

	@NotNull
	private Integer duree;

	private Integer capillaire;

	@Column(name = "restitution_p")
	private Integer restitutionP;

	@Column(name = "niveau_urgence")
	private Integer niveauUrgence;

	@Column(name = "uf_totale")
	private Double ufTotale;

	@Column(name = "rincage")
	private Integer rincage;

	@Column(name = "transfusion")
	private Integer transfusion;

	@Column(name = "date_planification")
	private LocalDate datePlanification;

	@Column(name = "circuit")
	private Integer circuit;

	@Column(name = "abord_vasculaire")
	private Integer abordVasculaire;

	@Column(name = "profil")
	private Integer profil;

	@Column(name = "conductivite_p")
	private Double conductiviteP;

	@Column(name = "debit_pompe")
	private Double debitPompe;

	@Column(name = "temperature_dialysat")
	private Double temperatureDialysat;

	@Column(name = "atc")
	private Boolean atc;

	@Column(name = "hnfh_0")
	private Double hnfh0;

	@Column(name = "hnfh_2")
	private Double hnfh2;

	@Column(name = "hbpm")
	private Double hbpm;

	@Enumerated(EnumType.STRING)
	@Column(name = "statut")
	private StatutPrescription statut = StatutPrescription.AVENIR;

	@Column(name = "motif_annulation")
	private Integer motifAnnulation;

	@Column(name = "motif_report")
	private Integer motifReport;

	@Column(name = "observation_p")
	private String observationP;

	@OneToOne
	@JoinColumn(unique = true)
	private TraitementPerdialyse traitement;

	@NotNull
	@ManyToOne
	@JsonIgnoreProperties(value = "prescriptions", allowSetters = true)
	private DossierPatient patient;

}
