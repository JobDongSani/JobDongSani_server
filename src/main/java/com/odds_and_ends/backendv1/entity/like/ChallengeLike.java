package com.odds_and_ends.backendv1.entity.like;

import com.odds_and_ends.backendv1.entity.challenge.Challenge;
import com.odds_and_ends.backendv1.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class ChallengeLike {

    @EmbeddedId
    private ChallengeLikeId likeId;

    @MapsId("challengeId")
    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

}
