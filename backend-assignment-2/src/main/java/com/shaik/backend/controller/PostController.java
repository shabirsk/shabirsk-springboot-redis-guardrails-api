package com.shaik.backend.controller;

import com.shaik.backend.entity.Post;
import com.shaik.backend.entity.Comment;
import com.shaik.backend.repository.PostRepository;
import com.shaik.backend.repository.CommentRepository;
import com.shaik.backend.service.ViralityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ViralityService viralityService;

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @PostMapping("/{postId}/comments")
    public Comment addComment(@PathVariable Long postId, @RequestBody Comment comment) {

        if (comment.getDepthLevel() > 20) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Max depth level is 20"
            );
        }

        if (comment.getAuthorId() != null && comment.getAuthorId() > 1000) {

            boolean allowed = viralityService.canBotReply(postId);

            if (!allowed) {
                throw new ResponseStatusException(
                    HttpStatus.TOO_MANY_REQUESTS,
                    "Bot limit reached (100)"
                );
            }

            boolean cooldown = viralityService.canBotReplyWithCooldown(postId, comment.getAuthorId());

            if (!cooldown) {
                throw new ResponseStatusException(
                    HttpStatus.TOO_MANY_REQUESTS,
                    "Bot cooldown active (10 min)"
                );
            }

            viralityService.incrementScore(postId, "BOT");

        } else {
            viralityService.incrementScore(postId, "COMMENT");
        }

        comment.setPostId(postId);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    @PostMapping("/{postId}/like")
    public String likePost(@PathVariable Long postId) {
        viralityService.incrementScore(postId, "LIKE");
        return "Post " + postId + " liked!";
    }
}