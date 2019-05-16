package com.share.memories.application.posts;

import com.share.memories.application.shared.Image;
import lombok.Value;

@Value
public class UserContext {
    private final String email;
    private final String firstName;
    private final String lastName;
    private final Image avatar;
}
