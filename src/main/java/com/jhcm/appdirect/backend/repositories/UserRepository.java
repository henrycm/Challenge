package com.jhcm.appdirect.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhcm.appdirect.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
