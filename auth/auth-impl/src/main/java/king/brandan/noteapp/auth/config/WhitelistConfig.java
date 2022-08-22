package king.brandan.noteapp.auth.config;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class WhitelistConfig {

	private static final List<String> APP_WHITELIST = List.of(
		"/v1/authenticate", "/v1/refresh-token"
	);

	public String[] getWhitelist() {
		return Stream.of(
			APP_WHITELIST.stream()
		).flatMap(x -> x).toArray(String[]::new);
	}

}
