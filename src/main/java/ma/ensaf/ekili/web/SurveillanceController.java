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

import lombok.RequiredArgsConstructor;
import ma.ensaf.ekili.domain.Surveillance;
import ma.ensaf.ekili.service.SurveillanceService;
import ma.ensaf.support.exception.NotFoundException;

@RequiredArgsConstructor

@RestController
@RequestMapping("/surveillance")
public class SurveillanceController {

	private final SurveillanceService surveillanceService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping // <=>	@RequestMapping(method = RequestMethod.POST)
	public Surveillance create(@RequestBody @Valid Surveillance entity) {
		return surveillanceService.create(entity);
	}
	
	@PutMapping
	public Surveillance update(@RequestBody @Valid Surveillance entity) {
		return surveillanceService.update(entity);
	}

	@GetMapping
	public Page<Surveillance> findAll(Pageable pageable) {
		return surveillanceService.findAll(pageable);
	}

	@GetMapping("/{id}")
	public Surveillance findById(@PathVariable Long id) {
		Optional<Surveillance> suvreillance = surveillanceService.findById(id);
		return suvreillance.orElseThrow(NotFoundException::new);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Long ident) {
		surveillanceService.deleteById(ident);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{id}/close")
	public void close(@PathVariable Long id) {
		surveillanceService.close(id);
		
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{id}/cancel")
	public void cancel(@PathVariable Long id) {
		surveillanceService.cancel(id);
		
	}
}
