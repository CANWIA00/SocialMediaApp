package com.canwia.askquestion.request;

import lombok.Data;

@Data
public class LikeCreateRequest {
    private Long id;
    private Long postId;
    private Long userId;
}
