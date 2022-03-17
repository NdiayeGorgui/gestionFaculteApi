package com.myproject.faculte.service;

import java.util.List;

import com.myproject.faculte.model.Role;

import com.myproject.faculte.model.User;

public interface UserService {
	User addNewUser(User user);
	Role addNewRole(Role role);
	User findUserByUserName(String userName);
	Role findRoleByRoleName(String roleName);
	void addRoleToUser(String userName,String roleName);
	User userAuthenticate(String userName, String password);
	List<User> getAllUsers();

}
