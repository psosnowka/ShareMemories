package com.share.memories.application.posts.dto;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString
public class AppUserContext {
    private final String uuid;
    private final String email;
}
