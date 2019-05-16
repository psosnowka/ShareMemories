package com.share.memories.application.posts;

import com.share.memories.application.posts.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
