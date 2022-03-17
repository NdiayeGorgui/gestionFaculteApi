package com.myproject.faculte.service;

import java.util.List;
import java.util.UUID;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.myproject.faculte.model.Role;
import com.myproject.faculte.model.User;
import com.myproject.faculte.repository.RoleRepository;
import com.myproject.faculte.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor //pour faire l'injection des dépendences
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	

	@Override
	public User addNewUser(User user) {
		//todo hacher le pwd
		user.setUserId(UUID.randomUUID().toString());
		return userRepository.save(user);
	}

	@Override
	public Role addNewRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public User findUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public Role findRoleByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		User user=findUserByUserName(userName);
		Role role=findRoleByRoleName(roleName);
		if(user.getRoles()!=null) {
			user.getRoles().add(role); //on va aller vers la collection des roles de l'utilisateur et on ajoute le role
			//userRepository.save(user); // c'est pas nécessaire car on a utiliser @Transactional, ya commit a la fin de la transaction
		}
		if(role.getUsers()!=null) {
			role.getUsers().add(user);  //vice versa
		}
		
	}

	@Override
	public User userAuthenticate(String userName, String password) {
		User user=userRepository.findByUserName(userName);
		if(user!=null  && user.getPassword().equals(password)) {
			return user;
		}
		throw new RuntimeException("Bad credential");
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
