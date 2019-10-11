package com.hcl.ingMortgage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.ingMortgage.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUserId(Integer userId);

}
