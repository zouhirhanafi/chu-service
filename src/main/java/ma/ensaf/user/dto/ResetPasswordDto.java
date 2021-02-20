package ma.ensaf.user.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {

	private String key;
	private String password;
}
