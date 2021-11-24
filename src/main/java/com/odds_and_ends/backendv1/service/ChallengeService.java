package com.odds_and_ends.backendv1.service;

import com.odds_and_ends.backendv1.entity.challenge.*;
import com.odds_and_ends.backendv1.entity.user.User;
import com.odds_and_ends.backendv1.exceptions.ChallengeAlreadyParticipateException;
import com.odds_and_ends.backendv1.facade.UserFacade;
import com.odds_and_ends.backendv1.payload.request.ChallengeRequest;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChallengeService {

    private final ChallengeUserRepository challengeUserRepository;
    private final UserFacade userFacade;
    private final ChallengeRepository challengeRepository;

    public CommonResponse<String> saveChallenge(ChallengeRequest request) {
        User currentUser = userFacade.getCurrentUser();

        Challenge challenge = buildChallenge(request);
        Challenge savedChallenge = challengeRepository.save(challenge);

        joinChallenge(savedChallenge, currentUser);

        return new CommonResponse<>(200, "챌린지 생성에 성공했습니다.", "data");
    }

    public CommonResponse<String> deleteChallenge(Long id) {
        User currentUser = userFacade.getCurrentUser();

        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(ChallengeAlreadyParticipateException::new);

        validateAlreadyParticipated(challenge, currentUser);
        joinChallenge(challenge, currentUser);

        return new CommonResponse<>(200, "챌린지 삭제에 성공했습니다.", id.toString());
    }

    private void joinChallenge(Challenge challenge, User user) {
        ChallengeUser challengeUser = ChallengeUser.builder()
                .challenge(challenge)
                .user(user)
                .build();

        challengeUserRepository.save(challengeUser);
    }

    private void validateAlreadyParticipated(Challenge challenge, User user) {
        ChallengeUserId challengeUserId = ChallengeUserId.builder()
                .challengeId(challenge.getId())
                .userId(user.getId())
                .build();
        challengeUserRepository.findById(challengeUserId)
                .ifPresent(it -> {
                    throw new ChallengeAlreadyParticipateException();
                });
    }

    private Challenge buildChallenge(ChallengeRequest request) {
        return Challenge.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .endDate(request.getEndAt())
                .startDate(request.getStartAt())
                .build();
    }

}
