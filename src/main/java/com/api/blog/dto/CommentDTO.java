package com.api.blog.dto;

import com.api.blog.entities.Post;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {

    private Long id;
    private String name;
    private String email;
    private String bodyMessage;

}
