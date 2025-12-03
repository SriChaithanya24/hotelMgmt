package com.restapiproject.hotelMgmt.auth;
 
import java.security.Key;
import java.util.Date;
 
import org.springframework.stereotype.Component;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
 
@Component
public class JwtUtil {	
	// helper -> create, validate and read JWT in app	
	// create a random secret key - signing JWT tokens using HS256 alogrithm
	// same key is used to sign AND verify JWT
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long expiration = 1000 * 60 * 60; // 1 Hour
	
	// Method for generating token
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(key)
				.compact(); // serialize the token to the string form header.payload.signature
	}
	
	// Method getUserNameFromToken
	public String getUserNameFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build()
				.parseClaimsJws(token).getBody().getSubject();			
				
	}
	
	// Validate Token
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		}
		catch (JwtException e) {
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
 
} 