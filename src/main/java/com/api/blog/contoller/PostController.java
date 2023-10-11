package com.api.blog.contoller;

import com.api.blog.dto.PostDTO;
import com.api.blog.dto.PostResponse;
import com.api.blog.service.PostService;
import com.api.blog.utils.AppConts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping()
    public ResponseEntity<PostResponse> getAll(
            @RequestParam(value = "pageNo", defaultValue = AppConts.DEFAULT_PAGE_NUM, required = false) int numPag,
            @RequestParam(value = "pageSize", defaultValue = AppConts.DEFAULT_PAGE_SIZE, required=false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConts.DEFAULT_ORDER_BY, required = false) String sortBy,
            @RequestParam(value = "sortAsc", defaultValue = AppConts.DEFAULT_ORDER_ASC, required = false) String sortAsc) {
        return new ResponseEntity<>(postService.getAll(numPag, pageSize, sortBy, sortAsc), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        PostDTO postResponse = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Publicación eliminada con éxito", HttpStatus.OK);
    }

}
