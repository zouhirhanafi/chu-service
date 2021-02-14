package ma.ensaf.ekili.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ma.ensaf.support.security.JwtRequestFilter;

@EnableWebSecurity
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

    @Override
    public void configure(WebSecurity web) {
        web
            .ignoring()
//            .antMatchers(HttpMethod.OPTIONS, "/**")
//            .antMatchers("/app/**/*.{js,html}")
//            .antMatchers("/i18n/**")
//            .antMatchers("/content/**")
            .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs")
//            .antMatchers("/test/**")
        ;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
		        .csrf().disable()
		        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
		        .sessionManagement()
		        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    .and()
				.authorizeRequests()
					.antMatchers("/authenticate", "/signup").permitAll()
					.antMatchers("/swagger-ui/**").permitAll()
					.anyRequest().authenticated()
			.and().exceptionHandling()
		;
        // Session
//		http.authorizeRequests()
//		.antMatchers("/admin/**").hasAuthority("ADMIN")
//		.antMatchers("/user").hasAuthority("USER")
//		.antMatchers("/bo").hasAnyAuthority("BO", "ADMIN")
//		.antMatchers("/auth").authenticated()
//		.antMatchers("/h2-console/**").permitAll()
//		.anyRequest().permitAll()
//	.and().formLogin()
//;


	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
