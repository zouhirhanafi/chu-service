package ma.ensaf.ekili.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import ma.ensaf.ekili.domain.enumeration.StatutPrescription;

@Data
public class PrescriptionDto {

	private Long id;

	@NotNull
	private Integer duree;

	private Integer capillaire;

	private Integer restitutionP;

	private Integer niveauUrgence;

	private Double ufTotale;

	private Integer rincage;

	private StatutPrescription statut;

}
