package ma.ensaf.support.exception.handler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import ma.ensaf.support.model.ErrorMessage;

// Spring 3.2
@ControllerAdvice
public class RestResponseEntityExceptionHandler /* extends ResponseEntityExceptionHandler */ {

	@ExceptionHandler({ NoSuchElementException.class, EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
		return handleException(ex, "entity.not_found", null, HttpStatus.NOT_FOUND, request);
	}

	protected ResponseEntity<Object> handleException(Exception ex, String message, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		ErrorMessage errorMessage = ErrorMessage.builder().error(ex.getMessage())
				.path(((ServletWebRequest) request).getRequest().getRequestURI().toString()).status(status.value())
				.message(message).trace(sw.toString()).build();

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(errorMessage, headers, status);
	}

//	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
//	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
//		String bodyOfResponse = "internal_error";
//		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
////		if (ex instanceof MethodArgumentNotValidException) {
////			status = HttpStatus.BAD_REQUEST;
////			bodyOfResponse = "data.not_valid";
////		}
//		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), status, request);
//	}

//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//		Map<String, Object> body = new LinkedHashMap<>();
//		body.put("timestamp", LocalDateTime.now());
//		body.put("status", status.value());
//
//		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
//				.collect(Collectors.toList());
//
//		body.put("errors", errors);
//
//		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//	}
}