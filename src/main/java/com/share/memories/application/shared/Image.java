package com.share.memories.application.shared;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Image {
    private final String url;

    public static Image of(String url) {
        return new Image(url);
    }
}
