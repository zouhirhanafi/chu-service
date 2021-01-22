package ma.ensaf.ekili.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;
import ma.ensaf.ekili.domain.enumeration.TypeTraitementPerdialyse;

/**
 * A TraitementPerdialyse.
 */
@Getter
@Setter

@Entity
@Table(name = "traitement_perdialyse")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TraitementPerdialyse extends AbstractPersistable<Long> {

	@Column(name = "autre")
	private String autre;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private TypeTraitementPerdialyse type;

}
