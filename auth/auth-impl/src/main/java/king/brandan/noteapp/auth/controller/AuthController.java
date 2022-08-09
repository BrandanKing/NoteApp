package king.brandan.noteapp.auth.controller;

import king.brandan.noteapp.auth.api.AuthRestApi;
import king.brandan.noteapp.auth.dto.AuthRequest;
import king.brandan.noteapp.auth.dto.AuthResponse;
import king.brandan.noteapp.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
public class AuthController implements AuthRestApi {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	@Override
	public ResponseEntity<AuthResponse> authenticate(AuthRequest request) {
		// Check if user is authenticated
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
		);
		log.info("{} {}", request.getUsername(), request.getPassword());
		AuthResponse authResponse = jwtService.createToken(
			(UserDetails) authentication.getPrincipal()
		);
		return ResponseEntity.ok().body(authResponse);
	}

}
