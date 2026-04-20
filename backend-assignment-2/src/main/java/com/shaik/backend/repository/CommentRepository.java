package com.shaik.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shaik.backend.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}