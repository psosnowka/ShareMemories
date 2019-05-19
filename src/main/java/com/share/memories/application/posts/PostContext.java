package com.share.memories.application.posts;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PostContext {
    private final String uuid;
    private final LocalDateTime createdDate;
    private final Owner owner;
    private final Content content;

    @Value
    public static class Content {
        private final String imageUrl;
        private final String title;
        private final String text;
    }

    @Value
    public static class Owner {
        private final String uuid;
        private final String firstName;
        private final String lastName;
        private final String imageUrl;
    }
}
