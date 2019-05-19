package com.share.memories.application.users;

import com.share.memories.application.base.BaseEntity;
import com.share.memories.application.shared.Image;
import com.share.memories.application.users.dto.AddUserRequest;
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

    UserContext getContext() {
        return new UserContext(getUuid(), this.email, this.firstName, this.lastName, Image.of(this.photoUrl));
    }

    static AppUser from(AddUserRequest request, PasswordEncoder passwordEncoder) {
        return new AppUser(request.getEmail(),
                           passwordEncoder.encode(request.getPassword()),
                           request.getFirstName(),
                           request.getLastName(),
                           request.getPhotoUrl());
    }
}
