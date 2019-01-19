package com.ensa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensa.entity.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
