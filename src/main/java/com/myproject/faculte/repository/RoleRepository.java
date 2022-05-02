package com.myproject.faculte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.faculte.model.Role;
import com.myproject.faculte.model.User;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRoleName(String roleName);
	 List<Role>findByUsersUserId(Long userId);
	 List<Role>findByUsersUserName(String userName);

}
