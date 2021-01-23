package ma.ensaf.support.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter

@MappedSuperclass
public class CustomAbstractPersistable<PK extends Serializable> extends AbstractPersistable<PK> {

	private Boolean deleted = false;
	@JsonIgnore
	private Boolean archived = false;

	@JsonIgnore
	@Override
	public boolean isNew() {
		return super.isNew();
	}
}
