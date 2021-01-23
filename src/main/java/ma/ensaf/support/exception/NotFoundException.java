package ma.ensaf.support.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Spring 3.0
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotFoundException() {
		super("entity.not_found");
	}

	public NotFoundException(String message) {
		super(message);
	}

}
