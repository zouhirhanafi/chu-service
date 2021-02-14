package ma.ensaf.support.model;

import lombok.Data;

@Data
public class UsernamePassword {
	
	private String username;
	private String password;
	private Boolean rememberMe;

}
