package com.odds_and_ends.backendv1.controller;

import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import com.odds_and_ends.backendv1.service.ChallengeLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final ChallengeLikeService likeService;

    @PutMapping("/{id}")
    public CommonResponse<String> doLike(@PathVariable Long id) {
        return likeService.saveChallengeLike(id);
    }

}