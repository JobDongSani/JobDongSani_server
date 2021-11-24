package com.odds_and_ends.backendv1.entity.comment;

import com.odds_and_ends.backendv1.entity.challenge.Challenge;
import com.odds_and_ends.backendv1.entity.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createDate;

    private LocalDate startDate;

    private LocalDate endDate;

    private String title;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;
}
