package com.share.memories.application.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface PostJpaRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();

    Optional<Post> findFirstByUuid(String uuid);
}
