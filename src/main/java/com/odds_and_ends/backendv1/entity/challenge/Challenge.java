package com.odds_and_ends.backendv1.entity.challenge;

import com.odds_and_ends.backendv1.entity.comment.Comment;
import com.odds_and_ends.backendv1.entity.like.ChallengeLike;
import com.odds_and_ends.backendv1.entity.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "writer")
    private User user;

    @CreatedDate
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "challenge")
    private List<Comment> comments;

    @OneToMany(mappedBy = "challenge")
    private List<ChallengeLike> challengeLikes;
}
