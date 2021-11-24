package com.odds_and_ends.backendv1.payload.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ChallengeRequest {

    private String title;

    private LocalDate startAt;

    private LocalDate endAt;

    private String content;

}
