package ma.ensaf.ekili.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

/**
 * A Antecedent.
 */

@Getter
@Setter

@Entity
@Table(name = "antecedent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Antecedent extends AbstractPersistable<Long> {

	private String autre;

}
