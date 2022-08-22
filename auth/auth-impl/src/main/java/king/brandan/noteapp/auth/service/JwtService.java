package king.brandan.noteapp.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import king.brandan.noteapp.auth.config.JwtConfig;
import king.brandan.noteapp.auth.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

	private final JwtConfig jwtConfig;

	public AuthResponse generateTokens(UserDetails userDetails) {
		String token = generateToken(userDetails, jwtConfig.getTokenExpirySecs());
		String refreshToken = generateToken(userDetails, jwtConfig.getTokenInvalidSecs());
		return new AuthResponse(token, refreshToken);
	}

	public String generateToken(UserDetails userDetails, long expirySecs) {
		return JWT.create()
			.withSubject(userDetails.getUsername())
			.withIssuedAt(Instant.now())
			.withExpiresAt(Date.from(Instant.now().plusSeconds(expirySecs)))
			.withIssuer("NotesApp")
			.withClaim("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
			.sign(jwtConfig.getAlgorithm());
	}

	public DecodedJWT verifyToken(String token) throws JWTVerificationException {
		return JWT.require(jwtConfig.getAlgorithm()).build().verify(token);
	}

	public static String extractJwtFromAuthHeader(String authHeader) {
		if (StringUtils.isBlank(authHeader) || !authHeader.startsWith("Bearer "))
			throw new BadCredentialsException("No token found");
		return authHeader.substring(7);
	}

	public String getUsernameFromToken(DecodedJWT token) {
		return token.getSubject();
	}

	public String[] getRolesFromToken(DecodedJWT token) {
		return token.getClaim("role").asArray(String.class);
	}

}
