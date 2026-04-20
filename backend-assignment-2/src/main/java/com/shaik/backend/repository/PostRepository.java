package com.shaik.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shaik.backend.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}