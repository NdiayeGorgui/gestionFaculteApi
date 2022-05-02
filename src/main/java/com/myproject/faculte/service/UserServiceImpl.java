package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;
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
		//user.setUserId(UUID.randomUUID().toString());
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

	@Override
	public List<Role> getAllRoles() {
		
		return roleRepository.findAll();
	}

	

	@Override
	public void deleteRoleToUser(String userName, String roleName) {
		User user=findUserByUserName(userName);
		Role role=findRoleByRoleName(roleName);
		if(user.getRoles()!=null) {
			user.getRoles().remove(role); 
			
		}
		if(role.getUsers()!=null) {
			role.getUsers().remove(user);  
		}
	}

	@Override
	public void  saveUserWihtRole(User user, String roleName) {
		User usr=addNewUser(user);
		Role role=findRoleByRoleName(roleName);
		if(usr.getRoles()!=null) {
			usr.getRoles().add(role); 
		}
		if(role.getUsers()!=null) {
			role.getUsers().add(usr);  
		}
					
	}

	

	@Override
	public void deleteRoleById(Long id) {
		roleRepository.deleteById(id);
		
	}

	@Override
	public Optional<Role> getRole(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserByUserName(String userName) {
		
		return Optional.ofNullable(userRepository.findByUserName(userName));
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public Optional<User> getUser(Long id) {
		
		return userRepository.findById(id);
	}

	@Override
	public List<User> findByRolesId(Long id) {
		
		return userRepository.findByRolesId(id);
	}

	@Override
	public List<Role> findByUsersUserId(Long userId) {
	
		return roleRepository.findByUsersUserId(userId);
	}

	@Override
	public List<Role> findByUsersUserName(String userName) {
		
		return roleRepository.findByUsersUserName(userName);
	}
}
