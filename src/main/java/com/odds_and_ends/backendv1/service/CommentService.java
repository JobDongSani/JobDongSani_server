package com.odds_and_ends.backendv1.service;

import com.odds_and_ends.backendv1.entity.challenge.Challenge;
import com.odds_and_ends.backendv1.entity.challenge.ChallengeRepository;
import com.odds_and_ends.backendv1.entity.comment.Comment;
import com.odds_and_ends.backendv1.entity.comment.CommentRepository;
import com.odds_and_ends.backendv1.exceptions.ChallengeNotFoundException;
import com.odds_and_ends.backendv1.facade.UserFacade;
import com.odds_and_ends.backendv1.payload.request.CommentRequest;
import com.odds_and_ends.backendv1.payload.response.CommentListResponse;
import com.odds_and_ends.backendv1.payload.response.CommentResponse;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ChallengeRepository challengeRepository;
    private final UserFacade userFacade;

    public CommonResponse<CommentListResponse> getCommentList(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(ChallengeNotFoundException::new);

        List<CommentResponse> challenges = challenge.getComments().stream()
                .map(this::buildCommentResponse)
                .collect(Collectors.toList());

        return new CommonResponse<>(200, "Comment 조회 성공", new CommentListResponse(challenges));
    }

    public CommonResponse<String> saveComment(Long challengeId, CommentRequest request) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(ChallengeNotFoundException::new);
        Comment savedComment = commentRepository.save(buildComment(request, challenge));
        return new CommonResponse<>(200, "Comment 생성 성공", savedComment.getId().toString());
    }

    private Comment buildComment(CommentRequest request, Challenge challenge) {
        return Comment.builder()
                .endDate(request.getEndDate())
                .title(request.getTitle())
                .user(userFacade.getCurrentUser())
                .startDate(request.getStartDate())
                .challenge(challenge)
                .imageUrl(request.getImageUrl())
                .build();
    }

    private CommentResponse buildCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .startDate(comment.getStartDate())
                .imageUrl(comment.getImageUrl())
                .name(comment.getUser().getName())
                .profileImage(comment.getUser().getProfileImage())
                .title(comment.getTitle())
                .username(comment.getUser().getUsername())
                .endDate(comment.getEndDate())
                .build();
    }
}
