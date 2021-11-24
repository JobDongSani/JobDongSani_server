package com.odds_and_ends.backendv1.entity.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
