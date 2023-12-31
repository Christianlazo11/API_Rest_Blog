package com.api.blog.service;

import com.api.blog.dto.PostDTO;
import com.api.blog.dto.PostResponse;

public interface PostService {

    public PostDTO createPost(PostDTO postDTO);

    public PostResponse getAll(int pageNum, int pageSize, String sortBy, String sortAsc);

    public PostDTO getPostById(Long id);

    public PostDTO updatePost(PostDTO postDTO, Long id);

    public void deletePost(Long id);

}
