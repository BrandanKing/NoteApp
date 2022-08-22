package king.brandan.noteapp.auth.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import king.brandan.noteapp.auth.api.AuthRestApi;
import king.brandan.noteapp.auth.config.JwtConfig;
import king.brandan.noteapp.auth.dto.AuthRequest;
import king.brandan.noteapp.auth.dto.AuthResponse;
import king.brandan.noteapp.auth.service.AuthService;
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

import javax.servlet.http.HttpServletRequest;

import static king.brandan.noteapp.auth.service.JwtService.extractJwtFromAuthHeader;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
public class AuthController implements AuthRestApi {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final AuthService authService;
	private final JwtConfig jwtConfig;

	@Override
	public ResponseEntity<AuthResponse> authenticate(AuthRequest request) {
		// Check if user is authenticated
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
		);
		log.info("Authenticate {}", request.getUsername());
		AuthResponse authResponse = jwtService.generateTokens(
			(UserDetails) authentication.getPrincipal()
		);
		return ResponseEntity.ok().body(authResponse);
	}

	@Override
	public ResponseEntity<AuthResponse> refreshToken(HttpServletRequest request) {
		final String authHeader = request.getHeader(AUTHORIZATION);
		final String refreshToken = extractJwtFromAuthHeader(authHeader);
		DecodedJWT decodedJWT = jwtService.verifyToken(refreshToken);
		String username = jwtService.getUsernameFromToken(decodedJWT);
		log.info("Generate Refresh token for {}", username);
		UserDetails userDetails = authService.loadUserByUsername(username);
		String token = jwtService.generateToken(userDetails, jwtConfig.getTokenExpirySecs());
		AuthResponse authResponse = new AuthResponse(token, refreshToken);
		return ResponseEntity.ok().body(authResponse);
	}

}
