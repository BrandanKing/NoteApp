package king.brandan.noteapp.auth.config;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class JwtConfig {

	@Value("${jwt.secret:notes-app-jwt-secret}")
	private String secret;

	@Value("${jwt.token.expiry.secs:28800}")
	private long tokenExpirySecs;

	@Value("${jwt.token.issue-expiry.secs:259200}")
	private long tokenInvalidSecs;

	public Algorithm getAlgorithm() {
		return Algorithm.HMAC256(secret.getBytes());
	}

}
