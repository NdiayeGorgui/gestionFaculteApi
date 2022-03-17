package com.myproject.faculte.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.faculte.model.User;
import com.myproject.faculte.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Gestion des Utilisateurs")
@RestController
public class UserRestController {
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Affiche la liste des utilisateurs")
	@RequestMapping(value = "/Users", method = RequestMethod.GET)
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@ApiOperation(value = "Récupère un un utilisateur selon son nom")
	@RequestMapping(value = "Users/{userName}", method = RequestMethod.GET)
	public User getUser(@PathVariable("userName") final String userName) {
		User user=userService.findUserByUserName(userName);
		return user;
		
	}

}
