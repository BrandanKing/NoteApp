package king.brandan.noteapp.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import king.brandan.noteapp.auth.config.JwtConfig;
import king.brandan.noteapp.auth.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtService {

	private final JwtConfig jwtConfig;

	public AuthResponse createToken(UserDetails userDetails) {

		Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret().getBytes());

		String token = JWT.create()
			.withSubject(userDetails.getUsername())
			.withIssuedAt(Instant.now())
			.withExpiresAt(Date.from(Instant.now().plusSeconds(jwtConfig.getTokenExpirySecs())))
			.withIssuer("NotesApp")
			.withClaim("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
			.sign(algorithm);

		String refreshToken = JWT.create()
			.withSubject(userDetails.getUsername())
			.withIssuedAt(Instant.now())
			.withExpiresAt(Date.from(Instant.now().plusSeconds(jwtConfig.getTokenInvalidSecs())))
			.withIssuer("NotesApp")
			.withClaim("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
			.sign(algorithm);

		return new AuthResponse(token, refreshToken);

	}

	public static Optional<String> extractJwtFromAuthHeader(String authHeader) {
		if (StringUtils.isBlank(authHeader) || !authHeader.startsWith("Bearer ")) return Optional.empty();
		return Optional.of(authHeader.substring(7));
	}

}
