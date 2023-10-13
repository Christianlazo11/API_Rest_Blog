package com.api.blog.contoller;

import com.api.blog.dto.CommentDTO;
import com.api.blog.entities.Comment;
import com.api.blog.service.CommentService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable(name = "postId", value = "postId") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }


    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> save(
            @PathVariable(name="postId", value = "postId") Long postId,
            @RequestBody CommentDTO commentDTO) {

        return new ResponseEntity<>(commentService.create(postId, commentDTO), HttpStatus.CREATED);
    }
}
