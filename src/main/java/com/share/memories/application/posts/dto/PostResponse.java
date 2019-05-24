package com.share.memories.application.posts.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PostResponse {
    private final String uuid;
    private final LocalDateTime createdDate;
    private final String text;
    private final String title;
    private final String imageUrl;
    private final boolean isPublic;
    private final String ownerUuid;
    private final String ownerEmail;
    private final String ownerImage;
    private final String ownerFirstName;
    private final String ownerLastName;
}
