package com.share.memories.application.users;

import com.share.memories.application.base.BaseEntity;
import com.share.memories.application.shared.Image;
import com.share.memories.application.users.dto.AddUserRequest;
import com.share.memories.application.users.dto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Column(length = 500)
    private String imageUrl;
    @ManyToMany
    private Set<AppUser> followers = new HashSet<>();

    UserContext getContext() {
        return new UserContext(getUuid(), this.email, this.firstName, this.lastName, Image.of(this.imageUrl));
    }

    static AppUser from(AddUserRequest request, PasswordEncoder passwordEncoder) {
        return new AppUser(request.getEmail(),
                           passwordEncoder.encode(request.getPassword()),
                           request.getFirstName(),
                           request.getLastName(),
                           request.getImage(),
                           new HashSet<>());
    }

    AppUser addFollower(AppUser appUser) {
        this.followers.add(appUser);
        return this;
    }

    AppUser deleteFollower(AppUser appUser) {
        this.followers.remove(appUser);
        return this;
    }

    UserResponse getUserResponse() {
        return new UserResponse(getUuid(),
                                this.email,
                                this.firstName,
                                this.lastName,
                                this.imageUrl,
                                this.followers.stream()
                                              .map(appUser -> new UserResponse.User(appUser.getUuid(),
                                                                                    appUser.getEmail(),
                                                                                    appUser.getFirstName(),
                                                                                    appUser.getLastName(),
                                                                                    appUser.getImageUrl()))
                                              .collect(Collectors.toSet()));
    }

    boolean isFollower(String uuid) {
        return followers.stream()
                        .anyMatch(appUser -> appUser.getUuid().equals(uuid));
    }
}
