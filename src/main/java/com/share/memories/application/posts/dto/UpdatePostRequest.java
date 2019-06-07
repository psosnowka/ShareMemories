package com.share.memories.application.posts.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdatePostRequest {
    private String postUuid;
    private String text;
    private String title;
    private String imageUrl;
    private boolean isPublic;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UpdatePostRequest(@JsonProperty("postUuid") String postUuid,
                             @JsonProperty("text") String text,
                             @JsonProperty("title") String title,
                             @JsonProperty("imageUrl") String imageUrl,
                             @JsonProperty("isPublic") boolean isPublic) {
        this.postUuid = postUuid;
        this.text = text;
        this.title = title;
        this.imageUrl = imageUrl;
        this.isPublic = isPublic;
    }
}
