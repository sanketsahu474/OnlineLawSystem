package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

	User findByResetToken(String resetToken);
	
	User findByName(String name);

	User findById(int id);

	void deleteById(int id);
}
