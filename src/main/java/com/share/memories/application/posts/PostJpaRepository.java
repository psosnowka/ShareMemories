package com.share.memories.application.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PostJpaRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();
}
