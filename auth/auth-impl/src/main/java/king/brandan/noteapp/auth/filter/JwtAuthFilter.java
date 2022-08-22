package king.brandan.noteapp.auth.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import king.brandan.noteapp.auth.config.WhitelistConfig;
import king.brandan.noteapp.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static king.brandan.noteapp.auth.service.JwtService.extractJwtFromAuthHeader;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
	private final WhitelistConfig whitelistConfig;
	private final JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {

		if (Arrays.asList(whitelistConfig.getWhitelist()).contains(request.getServletPath())) {
			filterChain.doFilter(request, response);
			return;
		}

		final String authHeader = request.getHeader(AUTHORIZATION);
		final String token = extractJwtFromAuthHeader(authHeader);
		DecodedJWT decodedJWT = jwtService.verifyToken(token);

		String username = jwtService.getUsernameFromToken(decodedJWT);
		String[] roles = jwtService.getRolesFromToken(decodedJWT);

		authoriseUser(username, roles);

		filterChain.doFilter(request, response);
	}

	private void authoriseUser(String username, String[] roles) {
		Collection<SimpleGrantedAuthority> authorities = Arrays.stream(roles)
			.map(SimpleGrantedAuthority::new).toList();

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
