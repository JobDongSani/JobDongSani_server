package com.odds_and_ends.backendv1.service;

import com.odds_and_ends.backendv1.entity.challenge.Challenge;
import com.odds_and_ends.backendv1.entity.challenge.ChallengeRepository;
import com.odds_and_ends.backendv1.entity.challenge.ChallengeUserRepository;
import com.odds_and_ends.backendv1.payload.request.ChallengeRequest;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChallengeService {

    private final ChallengeUserRepository challengeUserRepository;
    private final ChallengeRepository challengeRepository;

    public CommonResponse<String> saveChallenge(ChallengeRequest request) {
        Challenge challenge = Challenge.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .endDate(request.getEndAt())
                .startDate(request.getStartAt())
                .build();
        challengeRepository.save(challenge);

        return new CommonResponse<>(200, "챌린지 생성에 성공했습니다.", "data");
    }
}
