package com.api.blog.service.impl;

import com.api.blog.dto.PostDTO;
import com.api.blog.dto.PostResponse;
import com.api.blog.entities.Post;
import com.api.blog.exceptions.ResourceNotFoundException;
import com.api.blog.repository.PostRepository;
import com.api.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO) {

        Post post = this.mapPost(postDTO);

        Post newPost = postRepository.save(post);

        PostDTO postResponse = this.mapPostDTO(newPost);

        return postResponse;
    }

    @Override
    public PostResponse getAll(int pageNum, int pageSize, String sortBy, String sortAsc) {
        Sort sort = sortAsc.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNum, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);
        List<PostDTO> content = posts.stream().map(this::mapPostDTO).toList();

        PostResponse postResponse = PostResponse.builder()
                .content(content)
                .pageNum(posts.getNumber())
                .pageSize(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .lastPage(posts.isLast())
                .build();

        return postResponse;
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post postFind = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", id));

        return mapPostDTO(postFind);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        Post postFind = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", id));

        postFind.setTitle(postDTO.getTitle());
        postFind.setDescription(postDTO.getDescription());
        postFind.setContent(postDTO.getContent());

        Post postUpdate = postRepository.save(postFind);

        return mapPostDTO(postUpdate);
    }

    @Override
    public void deletePost(Long id) {
        Post postFind = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", id));

        postRepository.delete(postFind);
    }

    private PostDTO mapPostDTO(Post post) {
        PostDTO postDTO = PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .build();

        return postDTO;
    }

    private Post mapPost(PostDTO postDTO) {
        Post post = Post.builder()
                .id(postDTO.getId())
                .title(postDTO.getTitle())
                .description(postDTO.getDescription())
                .content(postDTO.getContent())
                .build();

        return post;
    }
}
