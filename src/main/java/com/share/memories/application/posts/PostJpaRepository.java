package com.share.memories.application.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface PostJpaRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.isPublic = true order by p.createdDate desc")
    List<Post> findAllPublicPosts();

    @Query("select p from Post p where p.ownerUuid in :users order by p.createdDate desc")
    List<Post> findAllPostsForUsers(@Param("users") List<String> users);

    Optional<Post> findFirstByUuid(String uuid);

    Optional<Post> getPostByUuid(String uuid);
}
