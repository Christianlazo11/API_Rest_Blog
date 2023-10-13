package com.api.blog.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
    private List<PostDTO> content;
    private int pageNum;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean lastPage;
}
