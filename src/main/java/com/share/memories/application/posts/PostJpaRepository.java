package com.share.memories.application.posts;

import org.springframework.data.jpa.repository.JpaRepository;

interface PostJpaRepository extends JpaRepository<Post, Long> {
}
