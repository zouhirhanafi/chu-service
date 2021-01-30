package ma.ensaf.ekili.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import ma.ensaf.ekili.aop.LogExecutionTime;
import ma.ensaf.ekili.domain.DossierPatient;
import ma.ensaf.ekili.service.DossierPatientService;

@RestController
@RequestMapping("/patient")
public class DossierPatientController {

	@Autowired
	private DossierPatientService dossierPatientService;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public <S extends DossierPatient> S save(@Valid @RequestBody S entity) {
		return dossierPatientService.save(entity);
	}

	@LogExecutionTime
	@PutMapping
	public <S extends DossierPatient> S update(@Valid @RequestBody S entity) {
		return dossierPatientService.update(entity);
	}

	@GetMapping
	public Page<DossierPatient> findAll(Pageable pageable) {
		return dossierPatientService.findAll(pageable);
	}

	@GetMapping("/{id}")
	public DossierPatient findById(@PathVariable Long id) {
		return dossierPatientService.findById(id).get();
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Long ident) {
		dossierPatientService.deleteById(ident);
	}
	
	
}
