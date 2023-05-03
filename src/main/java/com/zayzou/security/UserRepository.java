/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : UserRepository.java
 */

package com.zayzou.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Integer, String> {
}
