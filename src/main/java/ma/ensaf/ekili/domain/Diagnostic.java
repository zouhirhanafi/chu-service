package ma.ensaf.ekili.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

/**
 * A Diagnostic.
 */

@Getter
@Setter

@Entity
@Table(name = "diagnostic")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Diagnostic extends AbstractPersistable<Long> {

	private Integer hvb;

	private Integer hvc;

	private Integer vih;

	@Column(name = "poid_sec")
	private Double poidSec;

	private String autre;

}
