/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : UserRepository.java
 */

package com.zayzou.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
