package ma.ensaf.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensaf.user.config.Constants;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Data
public class UserDto {

	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String username;

	@NotNull
	@Size(max = 20)
	private String password;

	@Size(max = 50)
	private String firstName;

	@Size(max = 50)
	private String lastName;

	@Email
	@Size(min = 5, max = 254)
	private String email;

	@Size(min = 2, max = 10)
	private String langKey;

	@Size(max = 256)
	private String imageUrl;

}
