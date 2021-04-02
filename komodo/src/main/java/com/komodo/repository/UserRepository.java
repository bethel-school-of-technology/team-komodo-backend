//What Hans added to this came from Exeter Java Front-End Integration Lesson 3
package com.komodo.repository;

import com.komodo.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}