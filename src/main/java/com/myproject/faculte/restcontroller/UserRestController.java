package com.myproject.faculte.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.faculte.exception.IdIntrouvableExecption;
import com.myproject.faculte.model.Role;
import com.myproject.faculte.model.User;
import com.myproject.faculte.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:4200")
@Api(description = "Gestion des Utilisateurs")
@RestController
public class UserRestController {
	@Autowired
	private UserService userService;

	@ApiOperation(value = "Affiche la liste des utilisateurs")
	@RequestMapping(value = "/Users", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@ApiOperation(value = "Récupère un un utilisateur selon son nom")
	@RequestMapping(value = "Users/{userName}", method = RequestMethod.GET)
	public User getUserByUserName(@PathVariable("userName") final String userName) {
		User user = userService.findUserByUserName(userName);
		return user;

	}

	@ApiOperation(value = "Récupère un un utilisateur selon son id")
	@RequestMapping(value = "Users/User/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable("id") final Long id) throws IdIntrouvableExecption {
		Optional<User> user = userService.getUser(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new IdIntrouvableExecption("Le user avec l'id " + id + " n'existe pas");
		}
	}

	@ApiOperation(value = "Supprimer un user selon son id")
	@RequestMapping(value = "Users/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

	@ApiOperation(value = "Modifier un user selon son id")
	@RequestMapping(value = "Users/{id}", method = RequestMethod.PUT)
	public User updateUser(@PathVariable("id") final Long id, @RequestBody User user) {
		Optional<User> usr = userService.getUser(id);
		if (usr.isPresent()) {
			User currentUser = usr.get();
			
			String userName = user.getUserName();
			if (userName != null) {
				currentUser.setUserName(userName);
			}
			String password = user.getPassword();
			if (password != null) {
				currentUser.setPassword(password);
			}

			userService.addNewUser(currentUser);
			return currentUser;
		} else {
			return null;
		}
	}
	
	/*
	 * @ApiOperation(value = "Modifier un  User")
	 * 
	 * @RequestMapping(value = "/Users", method = RequestMethod.PUT) public User
	 * modifUser(@RequestBody User user) { return userService.updateUser(user); }
	 */

	@ApiOperation(value = "Ajouter un nouveau User")
	@RequestMapping(value = "/Users", method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		return userService.addNewUser(user);
	}

	@ApiOperation(value = "Affiche la liste des roles")
	@RequestMapping(value = "/Roles", method = RequestMethod.GET)
	public List<Role> getAllRoles() {
		return userService.getAllRoles();
	}

	@ApiOperation(value = "Ajouter un nouveau Role")
	@RequestMapping(value = "/Roles", method = RequestMethod.POST)
	public Role createRole(@RequestBody Role role) {
		return userService.addNewRole(role);
	}
	
	@ApiOperation(value = "Récupère un un role selon son nom")
	@RequestMapping(value = "Roles/{roleName}", method = RequestMethod.GET)
	public Role getroleByRolName(@PathVariable("roleName") final String roleName) {
		Role role = userService.findRoleByRoleName(roleName);
		return role;

	}
	@ApiOperation(value = "Récupère un un role selon son id")
	@RequestMapping(value = "Roles/Role/{id}", method = RequestMethod.GET)
	public Role getRoleById(@PathVariable("id") final Long id) throws IdIntrouvableExecption {
		Optional<Role> role = userService.getRole(id);
		if (role.isPresent()) {
			return role.get();
		} else {
			throw new IdIntrouvableExecption("Le role avec l'id " + id + " n'existe pas");
		}
	}
	
	@ApiOperation(value = "Supprimer un role selon son id")
	@RequestMapping(value = "Roles/{id}", method = RequestMethod.DELETE)
	public void deleteRole(@PathVariable("id") Long id) {
		userService.deleteRoleById(id);
	}
	
	@ApiOperation(value = "Modifier un role selon son id")
	@RequestMapping(value = "Roles/{id}", method = RequestMethod.PUT)
	public Role updateRole(@PathVariable("id") final Long id, @RequestBody Role role) {
		Optional<Role> rol = userService.getRole(id);
		if (rol.isPresent()) {
			Role currentRole = rol.get();

			String roleName = role.getRoleName();
			if (roleName != null) {
				currentRole.setRoleName(roleName);
			}
			

			userService.addNewRole(currentRole);
			return currentRole;
		} else {
			return null;
		}
	}
	
	@ApiOperation(value = "Ajoute un role à un utilisateur")
	@RequestMapping(value = "Users/addRoleToUser/{userName}/{roleName}", method = RequestMethod.POST)
	public void addRoleToUser(@PathVariable("userName") String userName, @PathVariable("roleName") String roleName) {
	
		userService.addRoleToUser(userName, roleName);

	}
	
	@ApiOperation(value = "supprime un role d'un utilisateur")
	@RequestMapping(value = "Users/deleteRoleToUser/{userName}/{roleName}", method = RequestMethod.DELETE)
	public void deleteRoleToUser(@PathVariable("userName") String userName, @PathVariable("roleName") String roleName) {
		 
		userService.deleteRoleToUser(userName, roleName);

	}
	
	@ApiOperation(value = "Ajoute  un utilisateur et l'affecter un role")
	@RequestMapping(value = "Users/addUserWithRole/{roleName}", method = RequestMethod.POST)
	public void saveUserWihtRole(@Valid @RequestBody  User user, @PathVariable("roleName") String roleName) {
	
		userService.saveUserWihtRole(user, roleName);

	}
	@ApiOperation(value = "Affiche la liste des  Users  pour un role selon son id")
	@RequestMapping(value = "/Users/Role/{id}", method = RequestMethod.GET)
	public List<User> findByRolesId(@PathVariable("id") Long id) {
		return userService.findByRolesId(id);
	}
	@ApiOperation(value = "Affiche la liste des  Users  pour un role selon son nom")
	@RequestMapping(value = "/Users/Role/UserName/{userName}", method = RequestMethod.GET)
	public List<Role> findByRolesId(@PathVariable("userName") String userName) {
		return userService.findByUsersUserName(userName);
	}
	
	@ApiOperation(value = "Affiche la liste des  Roles  pour un user selon son id")
	@RequestMapping(value = "/Roles/User/{id}", method = RequestMethod.GET)
	public List<Role> findByUsersUserId(@PathVariable("id") Long id) {
		return userService.findByUsersUserId(id);
	}

}
