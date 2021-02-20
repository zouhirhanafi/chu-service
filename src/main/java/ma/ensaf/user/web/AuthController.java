package ma.ensaf.user.web;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ma.ensaf.support.model.UsernamePassword;
import ma.ensaf.support.security.AuthenticationService;
import ma.ensaf.support.security.SecurityConstants;
import ma.ensaf.user.domain.User;
import ma.ensaf.user.dto.ResetPasswordDto;
import ma.ensaf.user.dto.UserDto;
import ma.ensaf.user.service.UserService;

@RestController
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/signup")
	public Object Signup(@RequestBody UserDto userDto) {
		User user = userService.create(userDto);
		String token = authenticationService.signup(user);
		return ResponseEntity.ok()
				.header(SecurityConstants.AUTHORIZATION_HEADER, token)
				.body(user)
		;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<Object> authenticate(@RequestBody UsernamePassword usernamePassword) {
//		user = userService.create(user);
		String token = authenticationService.authenticate(usernamePassword);
		return ResponseEntity.ok()
				.header(SecurityConstants.AUTHORIZATION_HEADER, token)
				.body(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
		;
	}
	
	@GetMapping("/account/activate")
	public ResponseEntity<Object> activate(String key) {
		userService.activate(key);
		return ResponseEntity.ok()
				.body("Votre inscription vient d etre validé")
		;
	}
	
	@GetMapping("/auth/reset-password-init")
	public ResponseEntity<Object> resetPasswordInit(String email) {
		userService.resetPasswordInit(email);
		return ResponseEntity.noContent().build();
	}

//	@PostMapping("/auth/reset-password-finish")
//	public ResponseEntity<Object> resetPasswordFinish(@RequestBody ResetPasswordDto dto) {
//		userService.resetPasswordFinish(dto);
//		return ResponseEntity.noContent().build();
//	}
	
	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
	}
}
