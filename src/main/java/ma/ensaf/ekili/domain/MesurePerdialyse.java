package ma.ensaf.ekili.domain;

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
 * A MesurePerdialyse.
 */
@Getter
@Setter

@Entity
@Table(name = "mesure_perdialyse")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MesurePerdialyse extends AbstractPersistable<Long> {

	@Column(name = "heure")
	private String heure;

	@Column(name = "poid")
	private Double poid;

	@Column(name = "ta")
	private String ta;

	@Column(name = "tp")
	private Double tp;

	@Column(name = "dextro")
	private Double dextro;

	@Column(name = "pa")
	private String pa;

	@Column(name = "pv")
	private Double pv;

	@Column(name = "ptm")
	private Double ptm;

	@Column(name = "ufh")
	private Double ufh;

	@Column(name = "conductivite")
	private Double conductivite;

	@Column(name = "td")
	private Double td;

	@Column(name = "dps")
	private Double dps;

	@Column(name = "heparine")
	private Double heparine;

	@Column(name = "rincage")
	private Integer rincage;

	@Column(name = "transfusion")
	private Integer transfusion;

	@Column(name = "num_poche")
	private Integer numPoche;

	@ManyToOne
	@JsonIgnoreProperties(value = "mesurePerdialyses", allowSetters = true)
	private Surveillance surveillance;

}
