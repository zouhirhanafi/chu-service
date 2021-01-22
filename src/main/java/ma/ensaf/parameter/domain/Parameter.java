package ma.ensaf.parameter.domain;

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
 * A Parameter.
 */
@Getter
@Setter

@Entity
@Table(name = "parameter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Parameter extends AbstractPersistable<Long> {

	@Column(name = "label")
	private String label;

	@Column(name = "lib_2")
	private String lib2;

	@Column(name = "lib_3")
	private String lib3;

	@Column(name = "ref_externe")
	private String refExterne;

	@Column(name = "val_1")
	private String val1;

	@Column(name = "val_2")
	private String val2;

	@Column(name = "val_3")
	private String val3;

	@Column(name = "ordre")
	private Integer ordre;

	@ManyToOne
	@JsonIgnoreProperties(value = "parameters", allowSetters = true)
	private Parameter type;

	@ManyToOne
	@JsonIgnoreProperties(value = "parameters", allowSetters = true)
	private Parameter paraent;

}
