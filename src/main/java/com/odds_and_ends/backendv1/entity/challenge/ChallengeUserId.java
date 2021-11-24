package com.odds_and_ends.backendv1.entity.challenge;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class ChallengeUserId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "challenge_id")
    private Long challengeId;

}
