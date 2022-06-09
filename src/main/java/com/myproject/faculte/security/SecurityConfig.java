package com.myproject.faculte.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	BCryptPasswordEncoder bCryptpasswordEncoder;
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptpasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/login").permitAll();
		
		http.authorizeRequests().requestMatchers(EndpointRequest.toAnyEndpoint()).hasAuthority("ADMIN").and().httpBasic();
		http.authorizeRequests().antMatchers("/Users").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/Roles").hasAuthority("ADMIN");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/Cours/**").hasAnyAuthority("ADMIN","RESPONSABLE","ENSEIGNANT");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/Cours/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/Cours/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/Cours/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/TypeCours/**").hasAnyAuthority("ADMIN","RESPONSABLE","ENSEIGNANT");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/TypeCours/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/TypeCours/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/TypeCours/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/Enseignants/**").hasAnyAuthority("ADMIN","RESPONSABLE","ENSEIGNANT");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/Enseignants/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/Enseignants/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/Enseignants/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/Formations/**").hasAnyAuthority("ADMIN","RESPONSABLE","ENSEIGNANT");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/Formations/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/Formations/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/Formations/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/Groupes/**").hasAnyAuthority("ADMIN","RESPONSABLE","ENSEIGNANT");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/Groupes/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/Groupes/**").hasAnyAuthority("ADMIN","RESPONSABLE");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/Groupes/**").hasAnyAuthority("ADMIN","RESPONSABLE");

		http.authorizeRequests().anyRequest().authenticated();
		
		http.addFilter(new JWTAuthenticationFilter (authenticationManager())) ;// on ajoute le filtre de la classe JWTAuthenticationFilter
		
		//on ajoute le filtre JWTAuthorizationFilter
		http.addFilter(new JWTAuthenticationFilter (authenticationManager())) ;
		http.addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);//on dit a spring avant de passer au filtre UsernamePasswordAuthenticationFilter
		                                                                                              // de commencer par JWTAuthorizationFilter()

		
	}

}
