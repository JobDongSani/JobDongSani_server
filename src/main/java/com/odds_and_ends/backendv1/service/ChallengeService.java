package com.odds_and_ends.backendv1.service;

import com.odds_and_ends.backendv1.entity.challenge.*;
import com.odds_and_ends.backendv1.entity.user.User;
import com.odds_and_ends.backendv1.exceptions.ChallengeAlreadyParticipateException;
import com.odds_and_ends.backendv1.exceptions.UserCannotDeleteChallengeException;
import com.odds_and_ends.backendv1.facade.UserFacade;
import com.odds_and_ends.backendv1.payload.request.ChallengeRequest;
import com.odds_and_ends.backendv1.payload.response.ChallengeDetailResponse;
import com.odds_and_ends.backendv1.payload.response.ChallengeListResponse;
import com.odds_and_ends.backendv1.payload.response.ChallengeResponse;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import com.odds_and_ends.backendv1.service.enums.FilteringType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChallengeService {

    private final UserFacade userFacade;
    private final ChallengeRepository challengeRepository;

    public CommonResponse<String> saveChallenge(ChallengeRequest request) {
        User currentUser = userFacade.getCurrentUser();

        Challenge challenge = buildChallenge(request, currentUser);
        challengeRepository.save(challenge);

        return new CommonResponse<>(200, "챌린지 생성에 성공했습니다.", "data");
    }

    public CommonResponse<String> deleteChallenge(Long challengeId) {
        User currentUser = userFacade.getCurrentUser();

        Challenge challenge = getChallenge(challengeId);

        if (!challenge.getUser().equals(currentUser)) {
            throw new UserCannotDeleteChallengeException();
        }

        challengeRepository.delete(challenge);

        return new CommonResponse<>(200, "챌린지 삭제에 성공했습니다.", challenge.toString());
    }

    public CommonResponse<ChallengeDetailResponse> getDetailResponse(Long challengeId) {
        Challenge challenge = getChallenge(challengeId);
        User user = userFacade.getCurrentUser();

        ChallengeDetailResponse response = ChallengeDetailResponse.builder()
                .memberCount(challenge.getComments().size())
                .content(challenge.getContent())
                .endDate(challenge.getEndDate())
                .startDate(challenge.getStartDate())
                .title(challenge.getTitle())
                .likeCount(challenge.getChallengeLikes().size())
                .backgroundImage(challenge.getImageUrl())
                .name(user.getName())
                .profileImage(user.getProfileImage())
                .username(user.getUsername())
                .isLike(challenge.getChallengeLikes().stream().anyMatch(challengeLike -> challengeLike.getUser().equals(user)))
                .build();

        return new CommonResponse<>(200, "챌린지 조회에 성공했습니다.", response);
    }

    public CommonResponse<ChallengeListResponse> getChallengeList(FilteringType type, Pageable pageable) {
        Page<Challenge> challenges;

        if(type.equals(FilteringType.NEW)) {
            challenges = challengeRepository.findAllByOrderByCreateDateDesc(pageable);
        } else {
            challenges = challengeRepository.findAllByTitleContainingOrderByChallengeLikesDesc(pageable);
        }

        List<ChallengeResponse> responses = challenges.getContent().stream()
                .map(this::buildChallengeResponse)
                .collect(Collectors.toList());

        return new CommonResponse<>(200, "조회 성공", new ChallengeListResponse(responses));
    }

    public CommonResponse<ChallengeListResponse> findChallengesByTitle(String title, FilteringType type, Pageable pageable) {
        Page<Challenge> challenges;
        if(type.equals(FilteringType.NEW)) {
            challenges = challengeRepository.findAllByTitleContainingOrderByCreateDateDesc(title, pageable);
        } else {
            challenges = challengeRepository.findAllByTitleContainingOrderByChallengeLikesDesc(title, pageable);
        }

        List<ChallengeResponse> responses = challenges.getContent().stream()
                .map(this::buildChallengeResponse)
                .collect(Collectors.toList());

        return new CommonResponse<>(200, "조회 성공", new ChallengeListResponse(responses));
    }

    private Challenge buildChallenge(ChallengeRequest request, User user) {
        return Challenge.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .endDate(request.getEndDate())
                .startDate(request.getStartDate())
                .user(user)
                .imageUrl(request.getImageUrl())
                .build();
    }

    private Challenge getChallenge(Long challengeId) {
        return challengeRepository.findById(challengeId)
                .orElseThrow(ChallengeAlreadyParticipateException::new);
    }

    private ChallengeResponse buildChallengeResponse(Challenge challenge) {
        User user = userFacade.getCurrentUser();

        return ChallengeResponse.builder()
                .content(challenge.getContent())
                .name(user.getName())
                .memberCount(challenge.getComments().size())
                .id(challenge.getId())
                .endDate(challenge.getEndDate())
                .startDate(challenge.getStartDate())
                .username(user.getUsername())
                .profileImage(user.getProfileImage())
                .title(challenge.getTitle())
                .likeCount(challenge.getChallengeLikes().size())
                .backgroundImage(challenge.getImageUrl())
                .isLike(challenge.getChallengeLikes().stream().anyMatch(challengeLike -> challengeLike.getUser().equals(user)))
                .build();
    }

}
