package com.odds_and_ends.backendv1.entity.challenge;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Page<Challenge> findAllByOrderByCreateDateDesc(Pageable pageable);
    Page<Challenge> findAllByTitleLikeOrderByCreateDateDesc(String title, Pageable pageable);
}
