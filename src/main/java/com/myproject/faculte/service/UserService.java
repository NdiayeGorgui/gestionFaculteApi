package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import com.myproject.faculte.model.Role;

import com.myproject.faculte.model.User;

public interface UserService {
	User addNewUser(User user);
	User updateUser(User user);
	void saveUserWihtRole(User user,String roleName);
	Role addNewRole(Role role);
	User findUserByUserName(String userName);
	Role findRoleByRoleName(String roleName);
	void deleteRoleToUser(String userName,String roleName);
	void addRoleToUser(String userName,String roleName);
	User userAuthenticate(String userName, String password);
	List<User> getAllUsers();
	List<Role> getAllRoles();
	void deleteRoleById(Long id);
	void deleteUserById(Long id);
	User findUserByUserId(Long userId);
	Optional<User>  getUserByUserName(String userName);
	Optional<User>  getUser(Long id);
	Optional<Role>  getRole(Long id);


}
