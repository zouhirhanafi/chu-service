package ma.ensaf.ekili.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * A ExamenBioligique.
 */
@Getter
@Setter

@Entity
@Table(name = "examen_bioligique")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamenBioligique extends AbstractPersistable<Long> {

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "uree")
	private Double uree;

	@Column(name = "creat")
	private Double creat;

	@Column(name = "k")
	private Double k;

	@Column(name = "na")
	private Double na;

	@Column(name = "ca")
	private Double ca;

	@Column(name = "crp")
	private Double crp;

	@Column(name = "hb")
	private Double hb;

	@Column(name = "gb")
	private Double gb;

	@Column(name = "plt")
	private Double plt;

	@Column(name = "ac_hbs")
	private String acHbs;

	@Column(name = "ag_hbs")
	private Double agHbs;

	@Column(name = "hbc")
	private String hbc;

	@Column(name = "ac_hvc")
	private String acHvc;

	@Column(name = "vih")
	private String vih;

	@Column(name = "autre")
	private String autre;

	@ManyToOne
	@JsonIgnoreProperties(value = "examenBioligiques", allowSetters = true)
	private DossierPatient patient;

}
