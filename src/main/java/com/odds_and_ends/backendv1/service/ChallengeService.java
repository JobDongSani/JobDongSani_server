package com.odds_and_ends.backendv1.service;

import com.odds_and_ends.backendv1.entity.challenge.*;
import com.odds_and_ends.backendv1.entity.user.User;
import com.odds_and_ends.backendv1.exceptions.ChallengeAlreadyParticipateException;
import com.odds_and_ends.backendv1.facade.UserFacade;
import com.odds_and_ends.backendv1.payload.request.ChallengeRequest;
import com.odds_and_ends.backendv1.payload.response.ChallengeDetailResponse;
import com.odds_and_ends.backendv1.payload.response.ChallengeListResponse;
import com.odds_and_ends.backendv1.payload.response.ChallengeResponse;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public CommonResponse<String> deleteChallenge(Long challengeId) {
        User currentUser = userFacade.getCurrentUser();

        Challenge challenge = getChallenge(challengeId);

        validateAlreadyParticipated(challenge, currentUser);
        joinChallenge(challenge, currentUser);

        return new CommonResponse<>(200, "챌린지 삭제에 성공했습니다.", challenge.toString());
    }

    public CommonResponse<ChallengeDetailResponse> getDetailResponse(Long challengeId) {
        Challenge challenge = getChallenge(challengeId);

        ChallengeDetailResponse response = ChallengeDetailResponse.builder()
                .memberCount(challenge.getChallengeUserList().size())
                .content(challenge.getContent())
                .endDate(challenge.getEndDate())
                .startDate(challenge.getStartDate())
                .title(challenge.getTitle())
                .build();

        return new CommonResponse<>(200, "챌린지 조회에 성공했습니다.", response);
    }

    public CommonResponse<ChallengeListResponse> getChallengeList(Pageable pageable) {
        Page<Challenge> challenges = challengeRepository.findAllBy(pageable);

        List<ChallengeResponse> responses = challenges.getContent().stream()
                .map(this::buildChallengeResponse)
                .collect(Collectors.toList());

        return new CommonResponse<>(200, "조회 성공", new ChallengeListResponse(responses));
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

    private Challenge getChallenge(Long challengeId) {
        return challengeRepository.findById(challengeId)
                .orElseThrow(ChallengeAlreadyParticipateException::new);
    }

    private ChallengeResponse buildChallengeResponse(Challenge challenge) {
        return ChallengeResponse.builder()
                .content(challenge.getContent())
                .name(challenge.getContent())
                .memberCount(challenge.getChallengeUserList().size())
                .id(challenge.getId())
                .endDate(challenge.getEndDate())
                .startDate(challenge.getStartDate())
                .build();
    }

}
