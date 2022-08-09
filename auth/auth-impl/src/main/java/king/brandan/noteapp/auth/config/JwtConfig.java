package king.brandan.noteapp.auth.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class JwtConfig {

	@Value("${jwt.secret:notes-app-jwt-secret}")
	private String secret;

	@Value("${jwt.token.expiry.secs:60}")
	private long tokenExpirySecs;

	@Value("${jwt.token.issue-expiry.secs:259200}")
	private long tokenInvalidSecs;

}
