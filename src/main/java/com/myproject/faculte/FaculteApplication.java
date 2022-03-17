package com.myproject.faculte;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.myproject.faculte.model.Role;
import com.myproject.faculte.model.User;
import com.myproject.faculte.service.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class FaculteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaculteApplication.class, args);
	}
	/*
	 * @Bean CommandLineRunner start(UserService userService) {
	 * 
	 * return args -> { Stream.of("papi", "jules", "toufah","gogo") .forEach(user ->
	 * { User u=new User(); u.setUserName(user); u.setPassword("12345");
	 * userService.addNewUser(u); });
	 * 
	 * Stream.of("admin", "responsable", "enseignant") .forEach(role -> { Role r=new
	 * Role(); r.setRoleName(role); userService.addNewRole(r); });
	 * 
	 * userService.addRoleToUser("papi", "admin");
	 * userService.addRoleToUser("jules", "responsable");
	 * userService.addRoleToUser("toufah", "enseignant");
	 * userService.addRoleToUser("gogo", "admin"); }; }
	 */
}
