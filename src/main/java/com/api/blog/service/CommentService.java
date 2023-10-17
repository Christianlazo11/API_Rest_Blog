package com.api.blog.service;

import com.api.blog.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    public CommentDTO create(Long postId, CommentDTO commentDTO);

    public List<CommentDTO> getCommentsByPostId(Long postId);

    public CommentDTO getCommentById(Long postId, Long commentId);

    public CommentDTO updateComment(Long postId,Long commentId, CommentDTO commentDTO);

    public void deleteComment(Long postId, Long commentId);
}
