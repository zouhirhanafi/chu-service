package ma.ensaf.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.ensaf.user.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return new User(username, "pass", Arrays.asList());
		
		return userRepository.findByUsername(username).map(user -> {
			List<SimpleGrantedAuthority> auths = user.getAuthorities().stream()
					.map(auth -> new SimpleGrantedAuthority(auth.getName()))
					.collect(Collectors.toList());
			return new User(username, user.getPassword(), user.isActivated(), true, true, true, auths);
		}).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
		
	}

}
