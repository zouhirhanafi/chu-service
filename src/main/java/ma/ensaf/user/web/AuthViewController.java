package ma.ensaf.user.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ma.ensaf.user.dto.ResetPasswordDto;
import ma.ensaf.user.service.UserService;

@Controller
public class AuthViewController {

	@Autowired
	private UserService userService;

	@GetMapping("/auth/rp")
	public String resetPassword(String key, Model model) {
		ResetPasswordDto dto = new ResetPasswordDto();
		dto.setKey(key);
		model.addAttribute("user", dto);
		return "reset-password";
	}

	@GetMapping("/auth/rp/success")
	public String success() {
		return "reset-password-success";
	}
	
	public ResponseEntity<Object> resetPasswordFinish(@RequestBody ResetPasswordDto dto) {
		userService.resetPasswordFinish(dto);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/auth/reset-password-finish")
    public String addUser(@Valid ResetPasswordDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "reset-password";
        }
		userService.resetPasswordFinish(dto);
        return "redirect:/auth/rp/success";
    }

}
