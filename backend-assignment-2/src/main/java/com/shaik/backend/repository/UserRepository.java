package com.shaik.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shaik.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}