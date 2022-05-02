package com.myproject.faculte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myproject.faculte.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserName(String userName);
	User findByUserId(Long userId);
	
	//@Query("select r from Role, User u where u.userId like %:numUser")
	List<User> findByRolesId(Long id);
}
