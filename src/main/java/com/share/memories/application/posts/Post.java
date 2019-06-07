package com.share.memories.application.posts;

import com.share.memories.application.base.BaseEntity;
import com.share.memories.application.posts.dto.AddPostRequest;
import com.share.memories.application.posts.dto.PostResponse;
import com.share.memories.application.posts.dto.UpdatePostRequest;
import com.share.memories.application.users.UserContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Post extends BaseEntity {
    @Column(length = 1500)
    private String title;
    @Column(length = 1500)
    private String text;
    @Column(length = 1000)
    private String imageUrl;
    private boolean isPublic;
    private String ownerEmail;
    private String ownerFirstName;
    private String ownerLastName;
    @Column(length = 1000)
    private String ownerImageUrl;
    private String ownerUuid;

    static Post from(AddPostRequest request, UserContext userContext) {
        return new Post(request.getTitle(),
                        request.getText(),
                        request.getImageUrl(),
                        request.isPublic(),
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

    Post updatePost(UpdatePostRequest request) {
        this.imageUrl = request.getImageUrl();
        this.text = request.getText();
        this.title = request.getTitle();
        this.isPublic = request.isPublic();
        return this;
    }

    PostResponse getPostResponse() {
        return new PostResponse(
                this.getUuid(),
                this.getCreatedDate(),
                this.text,
                this.title,
                this.imageUrl,
                this.isPublic,
                this.ownerUuid,
                this.ownerEmail,
                this.ownerImageUrl,
                this.ownerFirstName,
                this.ownerLastName);
    }
}
