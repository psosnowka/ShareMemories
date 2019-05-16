package com.share.memories.application.posts;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PostContext {
    private final String uuid;
    private final LocalDateTime createdDate;
    private final UserContext userContext;
    private final Content content;


    @Value
    public static class Content {
        private final String imageUrl;
        private final String title;
        private final String text;
    }
}
