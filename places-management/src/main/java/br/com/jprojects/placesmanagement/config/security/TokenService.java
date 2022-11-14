package br.com.jprojects.placesmanagement.config.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.jprojects.placesmanagement.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	private String secret = "rfAz9*4D3980";

	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Date now = new Date();
		Date expiration = new Date(now.getTime() + Long.parseLong("1800000"));
		
		return Jwts.builder()
				.setIssuer("Places Management API")
				.setSubject(user.getId().toString())
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean tokenIsValid(String token) {
		
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public Integer getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		Integer userId = Integer.valueOf(claims.getSubject());
		return userId;
	}

}
