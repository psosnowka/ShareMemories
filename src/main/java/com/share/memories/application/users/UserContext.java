package com.share.memories.application.users;

import com.share.memories.application.shared.Image;
import lombok.Value;

@Value
public class UserContext {
    private final String uuid;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final Image avatar;

    public String getAvatarUrl() {
        return avatar.getUrl();
    }
}
