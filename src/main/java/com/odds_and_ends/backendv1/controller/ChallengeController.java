package com.odds_and_ends.backendv1.controller;

import com.odds_and_ends.backendv1.payload.request.ChallengeRequest;
import com.odds_and_ends.backendv1.payload.response.ChallengeDetailResponse;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import com.odds_and_ends.backendv1.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @GetMapping("/{id}")
    public CommonResponse<ChallengeDetailResponse> getChallengeDetail(@PathVariable Long id) {
        return challengeService.getDetailResponse(id);
    }

    @PostMapping
    public CommonResponse<String> saveChallenge(@RequestBody ChallengeRequest request) {
        return challengeService.saveChallenge(request);
    }

    @DeleteMapping("/{id}")
    public CommonResponse<String> deleteChallenge(@PathVariable Long id) {
        return challengeService.deleteChallenge(id);
    }

}
