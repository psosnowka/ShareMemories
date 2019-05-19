package com.share.memories.application.posts.dto;

import lombok.Data;

@Data
public class AddPostRequest {
    private String text;
    private String title;
    private String image;
}
