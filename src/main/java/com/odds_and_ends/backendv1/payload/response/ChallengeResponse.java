package com.odds_and_ends.backendv1.payload.response;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ChallengeResponse {

    private Long id;

    private String title;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private int memberCount;

    private int likeCount;

    private String profileImage;

    private String backgroundImage;

    private String username;

    private String name;

    private Boolean isLike;

}
