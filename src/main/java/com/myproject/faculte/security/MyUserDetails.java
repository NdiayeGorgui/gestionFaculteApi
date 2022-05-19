package com.myproject.faculte.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myproject.faculte.model.User;
import com.myproject.faculte.service.UserService;

@Service
public class MyUserDetails implements UserDetailsService{
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userService.findUserByUserName(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Utilisateur introuvable");
		}
		List<GrantedAuthority> auths=new ArrayList<>();
		user.getRoles().forEach(role->{
			GrantedAuthority authority=new SimpleGrantedAuthority(role.getRoleName());
			auths.add(authority);  //on ajoute les roles de ce user
		});
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), auths);
	}

}
