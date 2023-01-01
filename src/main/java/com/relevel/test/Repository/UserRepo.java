package com.relevel.test.Repository;

import com.relevel.test.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
//   
	 Optional<User> findByEmail(String username);
	 
	 
}
