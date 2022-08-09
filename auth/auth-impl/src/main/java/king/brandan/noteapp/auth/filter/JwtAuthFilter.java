package king.brandan.noteapp.auth.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import king.brandan.noteapp.auth.config.JwtConfig;
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
	private final JwtConfig jwtConfig;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {

		if (request.getServletPath().equals("/v1/authenticate")) {
			filterChain.doFilter(request, response);
		} else {

			final String authHeader = request.getHeader(AUTHORIZATION);
			final String authJwt = extractJwtFromAuthHeader(authHeader).orElseThrow(null);

			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtConfig.getSecret().getBytes())).build();
			DecodedJWT decodedJWT = verifier.verify(authJwt);

			String username = decodedJWT.getSubject();
			String[] roles = decodedJWT.getClaim("role").asArray(String.class);
			Collection<SimpleGrantedAuthority> authorities = Arrays.stream(roles)
				.map(SimpleGrantedAuthority::new).toList();

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			filterChain.doFilter(request, response);
		}
	}

}
