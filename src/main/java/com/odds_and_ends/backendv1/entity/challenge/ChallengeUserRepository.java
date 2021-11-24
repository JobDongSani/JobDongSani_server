package com.odds_and_ends.backendv1.entity.challenge;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeUserRepository extends JpaRepository<ChallengeUser, ChallengeUserId> {
}
