package com.api.blog.contoller;

import com.api.blog.dto.CommentDTO;
import com.api.blog.service.CommentService;
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

    @GetMapping("/post/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(
            @PathVariable(name = "postId", value = "postId") Long postId,
            @PathVariable(name = "id", value = "id") Long commentId) {

        CommentDTO commentDTO = commentService.getCommentById(postId, commentId);

        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }


    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> save(
            @PathVariable(name="postId", value = "postId") Long postId,
            @RequestBody CommentDTO commentDTO) {

        return new ResponseEntity<>(commentService.create(postId, commentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/post/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable(name = "postId", value = "postId") Long postId,
            @PathVariable(name = "id", value = "id") Long commentId,
            @RequestBody CommentDTO commentDTO) {

        CommentDTO commentUpdate = commentService.updateComment(postId, commentId, commentDTO);
        
        return new ResponseEntity<>(commentUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("/post/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable(name="postId", value = "postId") Long postId,
            @PathVariable(name = "id", value = "id") Long commentId) {

        commentService.deleteComment(postId, commentId);

        return new ResponseEntity<>("Comentario eliminado con éxito!", HttpStatus.OK);
    }


}
