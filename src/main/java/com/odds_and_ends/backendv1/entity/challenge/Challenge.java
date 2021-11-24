package com.odds_and_ends.backendv1.entity.challenge;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class Challenge {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "challenge")
    private List<ChallengeUser> challengeUserList;
}
