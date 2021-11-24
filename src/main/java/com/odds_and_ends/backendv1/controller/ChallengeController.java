package com.odds_and_ends.backendv1.controller;

import com.odds_and_ends.backendv1.payload.response.ChallengeDetailResponse;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import com.odds_and_ends.backendv1.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @GetMapping("/{id}")
    public CommonResponse<ChallengeDetailResponse> getChallengeDetail(@PathVariable Long id) {
        return challengeService.getDetailResponse(id);
    }
}
