package com.share.memories.application.posts.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class AddPostRequest {
    private boolean isPublic;
    private String text;
    private String title;
    private String imageUrl;

    @JsonCreator
    public AddPostRequest(boolean isPublic, String text, String title, String imageUrl) {
        this.isPublic = isPublic;
        this.text = text;
        this.title = title;
        this.imageUrl = imageUrl;
    }
}
