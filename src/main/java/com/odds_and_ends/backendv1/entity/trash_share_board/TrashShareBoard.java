package com.odds_and_ends.backendv1.entity.trash_share_board;

import com.odds_and_ends.backendv1.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Table(name = "trash_share_board")
@Builder
@NoArgsConstructor @AllArgsConstructor
public class TrashShareBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String contents;

    @Column(name = "image_path")
    private String imagePath;
}
