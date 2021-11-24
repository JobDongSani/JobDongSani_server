package com.odds_and_ends.backendv1.entity.challenge;

import com.odds_and_ends.backendv1.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class ChallengeUser {

    @EmbeddedId
    private ChallengeUserId id;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("challengeId")
    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

}
