package ma.ensaf.support.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {

	@Builder.Default
	private LocalDateTime timestamp = LocalDateTime.now();
	private Integer status;
	private String error;
	private String message;
	private String path;
	private Object trace;

}
