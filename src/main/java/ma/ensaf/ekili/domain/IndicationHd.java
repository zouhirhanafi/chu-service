package ma.ensaf.ekili.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

/**
 * A IndicationHd.
 */
@Getter
@Setter

@Entity
@Table(name = "indication_hd")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IndicationHd extends AbstractPersistable<Long> {

	private Integer service;

	private String autre;

}
