package com.canwia.askquestion.request;

import lombok.Data;

@Data
public class CommentCreateRequest {

    private Long id;
    private Long userId;
    private Long postId;
    private String text;
}
