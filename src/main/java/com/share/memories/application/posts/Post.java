package com.share.memories.application.posts;

import com.share.memories.application.base.BaseEntity;
import com.share.memories.application.posts.dto.AddPostRequest;
import com.share.memories.application.users.UserContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Post extends BaseEntity {
    private String title;
    private String text;
    private String imageUrl;
    private String ownerEmail;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerImageUrl;
    private String ownerUuid;

    static Post from(AddPostRequest request, UserContext userContext) {
        return new Post(request.getTitle(),
                        request.getText(),
                        request.getImage(),
                        userContext.getEmail(),
                        userContext.getFirstName(),
                        userContext.getLastName(),
                        userContext.getAvatarUrl(),
                        userContext.getUuid());
    }

    PostContext getPostContext() {
        return new PostContext(getUuid(),
                               getCreatedDate(),
                               new PostContext.Owner(this.ownerUuid,
                                                     this.ownerFirstName,
                                                     this.ownerLastName,
                                                     this.ownerImageUrl,
                                                     this.ownerEmail),
                               new PostContext.Content(this.imageUrl,
                                                       this.title,
                                                       this.text));
    }
}
