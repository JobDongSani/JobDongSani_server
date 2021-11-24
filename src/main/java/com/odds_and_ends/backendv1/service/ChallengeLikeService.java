package com.odds_and_ends.backendv1.service;

import com.odds_and_ends.backendv1.entity.challenge.Challenge;
import com.odds_and_ends.backendv1.entity.challenge.ChallengeRepository;
import com.odds_and_ends.backendv1.entity.like.ChallengeLike;
import com.odds_and_ends.backendv1.entity.like.ChallengeLikeId;
import com.odds_and_ends.backendv1.entity.like.ChallengeLikeRepository;
import com.odds_and_ends.backendv1.entity.user.User;
import com.odds_and_ends.backendv1.exceptions.ChallengeNotFoundException;
import com.odds_and_ends.backendv1.facade.UserFacade;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChallengeLikeService {

    private final ChallengeLikeRepository likeRepository;
    private final ChallengeRepository challengeRepository;
    private final UserFacade userFacade;

    public CommonResponse<String> saveChallengeLike(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(ChallengeNotFoundException::new);
        User currentUser = userFacade.getCurrentUser();

        saveOrUpdateLike(challenge, currentUser);

        return new CommonResponse<>(200, "좋아요 성공", challenge.getId().toString());
    }

    private ChallengeLike buildChallengeLike(Challenge challenge, User user) {
        return ChallengeLike.builder()
                .challenge(challenge)
                .user(user)
                .build();
    }

    private void saveLike(Challenge challenge, User currentUser) {
        ChallengeLike challengeLike = buildChallengeLike(challenge, currentUser);
        likeRepository.save(challengeLike);
    }

    private void saveOrUpdateLike(Challenge challenge, User user) {
        ChallengeLikeId likeId = ChallengeLikeId.builder()
                .challengeId(challenge.getId())
                .userId(user.getId())
                .build();

        likeRepository.findById(likeId)
                .ifPresentOrElse(likeRepository::delete,
                        () -> saveLike(challenge, user));
    }
}
