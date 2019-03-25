package com.share.memories.application.users;

import com.share.memories.application.base.BaseEntity;
import com.share.memories.application.users.dto.AppUserContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
class AppUser extends BaseEntity {
    private String email;
    private String password;

    AppUserContext getContext() {
        return AppUserContext.builder()
                             .email(getEmail())
                             .uuid(getUuid())
                             .build();
    }
}
