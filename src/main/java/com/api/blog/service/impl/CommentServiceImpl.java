package com.api.blog.service.impl;

import com.api.blog.dto.CommentDTO;
import com.api.blog.entities.Comment;
import com.api.blog.entities.Post;
import com.api.blog.exceptions.ResourceNotFoundException;
import com.api.blog.repository.CommentRepository;
import com.api.blog.repository.PostRepository;
import com.api.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDTO create(Long postId, CommentDTO commentDTO) {
        Comment comment = mapComment(commentDTO);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        return mapDTO(newComment);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(post -> mapDTO(post)).toList();
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {
        return null;
    }

    private CommentDTO mapDTO(Comment comment) {
        CommentDTO commentDTO = CommentDTO.builder()
                .id(comment.getId())
                .email(comment.getEmail())
                .name(comment.getName())
                .bodyMessage(comment.getBodyMessage())
                .build();

        return commentDTO;
    }

    private Comment mapComment(CommentDTO commentDTO) {
        Comment comment = Comment.builder()
                .id(commentDTO.getId())
                .email(commentDTO.getEmail())
                .name(commentDTO.getName())
                .bodyMessage(commentDTO.getBodyMessage())
                .build();

        return comment;
    }
}
