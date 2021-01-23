package ma.ensaf.ekili.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ma.ensaf.ekili.domain.Prescription;
import ma.ensaf.ekili.dto.PrescriptionDto;
import ma.ensaf.ekili.service.PrescriptionService;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

	private final PrescriptionService prescriptionService;
	// injection
	public PrescriptionController(PrescriptionService prescriptionService) {
		this.prescriptionService = prescriptionService;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping // <=>	@RequestMapping(method = RequestMethod.POST)
	public Prescription create(@RequestBody @Valid Prescription entity) {
		return prescriptionService.create(entity);
	}
	
	@PutMapping
	public Prescription update(@RequestBody @Valid Prescription entity) {
		return prescriptionService.update(entity);
	}

	@GetMapping
	public Page<PrescriptionDto> findAll(Pageable pageable) {
		return prescriptionService.findAll(pageable);
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<?> findById(@PathVariable Long id) {
//		Optional<Prescription> prescription = prescriptionService.findById(id);
//		if (prescription.isPresent()) {
//			return ResponseEntity.ok().body(prescription.get());
//		}
//		ErrorMessage errorMessage = ErrorMessage.builder().message("prescription not found").status(404).path("/prescritption/" + id).build();
//		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
////		prescription.filter(p -> !p.getDeleted() && !p.getArchived()).ifPresent(p -> {
////			p.setCapillaire(2);
////			// ...
////		});
////		return prescription.orElse(new Prescription());
//	}
	@GetMapping("/{id}")
	public Prescription findById(@PathVariable Long id) {
		Optional<Prescription> prescription = prescriptionService.findById(id);
		return prescription.get();
//		return prescription.orElseThrow(NotFoundException::new);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Long ident) {
		prescriptionService.deleteById(ident);
	}

}
