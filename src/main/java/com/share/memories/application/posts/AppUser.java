package com.share.memories.application.posts;

import com.share.memories.application.base.BaseEntity;
import com.share.memories.application.posts.dto.AddUserRequest;
import com.share.memories.application.posts.dto.AppUserContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
class AppUser extends BaseEntity {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String photoUrl;

    AppUserContext getContext() {
        return AppUserContext.builder()
                             .email(getEmail())
                             .uuid(getUuid())
                             .build();
    }

    static AppUser from(AddUserRequest request, PasswordEncoder passwordEncoder) {
        return new AppUser(request.getEmail(),
                           passwordEncoder.encode(request.getPassword()),
                           request.getFirstName(),
                           request.getLastName(),
                           request.getPhotoUrl());
    }
}
