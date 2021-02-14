package ma.ensaf.support.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenProvider {

    @Value("${app.security.secret-key}")
    private String secretKey;
    @Value("${app.security.token-validity:}")
    private Long tokenValidity;
    @Value("${app.security.remember-me-token-validity:}")
    private Long rememberMeTokenValidity;

    public String createToken(String username, String authorities, Boolean rememberMe) {
        boolean remember = ObjectUtils.nullSafeEquals(rememberMe, true);
        long now = (new Date()).getTime();
        Date validity = null;
        if (remember && rememberMeTokenValidity != null) {
            validity = new Date(now + rememberMeTokenValidity * 1000);
        } else if (!remember && tokenValidity != null) {
            validity = new Date(now + tokenValidity * 1000);
        }

        JwtBuilder jwtBuilder = Jwts
            .builder()
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .setSubject(username)
            .claim(SecurityConstants.AUTHORITIES_KEY, authorities)
            .setIssuedAt(new Date())
        ;
        if (validity != null) {
        	jwtBuilder.setExpiration(validity);
        }
        return jwtBuilder.compact();
    }

    public String createToken(Authentication authentication, Boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()
        			.map(GrantedAuthority::getAuthority)
        			.collect(Collectors.joining(","));
        return createToken(authentication.getName(), authorities, rememberMe);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
    
    public Authentication getAuthentication(String token) {
        Claims claims = extractAllClaims(token);
        Object auths = claims.get(SecurityConstants.AUTHORITIES_KEY);
        Collection<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (!ObjectUtils.isEmpty(auths)) {
        	authorities = Arrays
        			.stream(auths.toString().split(","))
        			.map(SimpleGrantedAuthority::new)
        			.collect(Collectors.toList());
        }
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }

    public boolean validateToken(String token) {
        try {
        	Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.debug("Invalid JWT token : {}", e.getMessage());
        }
        return false;
    }
}
