package king.brandan.noteapp.auth.api;

import king.brandan.noteapp.auth.dto.AuthRequest;
import king.brandan.noteapp.auth.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping()
public interface AuthRestApi {

	@PostMapping("/v1/authenticate")
	ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request);

	@PostMapping("/v1/refresh-token")
	ResponseEntity<AuthResponse> refreshToken(HttpServletRequest request);

}
