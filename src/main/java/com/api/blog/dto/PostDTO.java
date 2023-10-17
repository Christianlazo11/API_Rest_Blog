package com.api.blog.dto;


import com.api.blog.entities.Comment;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {

    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<Comment> comments;
}
