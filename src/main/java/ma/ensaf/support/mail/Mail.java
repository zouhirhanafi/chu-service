package ma.ensaf.support.mail;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Mail {
	
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String body;
	@Builder.Default
	private boolean html = false;
	
	private String templateName;
	private Map<String, Object> model;

}
