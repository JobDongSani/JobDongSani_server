package com.odds_and_ends.backendv1.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeListResponse {
    private List<ChallengeResponse> responses;
}
