package com.myproject.faculte.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

//cette classe verifie l'authencité du token jwt
 
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	//l'objectif de cette methode elle va extraire le token du request
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods",
		"GET,HEAD,OPTIONS,POST,PUT,DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, "
				+ "X-Requested-With, Content-Type, Access-Control-Request-Method,"
				+ "	Access-Control-Request-Headers, Authorization");
		response.addHeader("Access-Control-Expose-Headers","Authorization, Access-ControlAllow-Origin,Access-Control-Allow-Credentials ");
		if (request.getMethod().equals("OPTIONS"))
		{
		response.setStatus(HttpServletResponse.SC_OK);
		return;
		}
		String jwt = request.getHeader("Authorization");//on extrait l'entete Authorization qu'on avait généré lors de la creation du token
		if (jwt == null || !jwt.startsWith(SecParams.PREFIX)) {  //PREFIX c le Bearer veut dire transporteur
			filterChain.doFilter(request, response);   //on passe au filtre suivant
			return;
		}
		
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecParams.SECRET)).build();
		// enlever le préfixe Bearer du jwt
		jwt = jwt.substring(SecParams.PREFIX.length()); //ca renvoie 7 caractères dans "Bearer "
		DecodedJWT decodedJWT = verifier.verify(jwt);  //je decode mon token et de verifier si c un bon token
		String username = decodedJWT.getSubject(); // subject veut dire L'utilisateur(username)
		List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class); //on recupere les roles a partir du token
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String r : roles)
			authorities.add(new SimpleGrantedAuthority(r)); //on ajoute les roles dans cette collection authorities
		//je transmet ces infos a spring security cad mettre a jour le context de spring security
		UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null, authorities);
		SecurityContextHolder.getContext().setAuthentication(user); //dire a spring sec ce user est bien authentifié
		filterChain.doFilter(request, response);
	}

}

