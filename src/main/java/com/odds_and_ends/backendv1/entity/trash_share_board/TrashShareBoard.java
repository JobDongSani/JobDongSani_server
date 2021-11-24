package com.odds_and_ends.backendv1.entity.trash_share_board;

import com.odds_and_ends.backendv1.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Table(name = "trash_share_board")
@Builder @Getter
@NoArgsConstructor @AllArgsConstructor
public class TrashShareBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String contents;

    private String contact;

    @Column(name = "image_path")
    private String imagePath;

    public void update(String title, String contents, String contact, String imagePath){
        this.title = title;
        this.contents = contact;
        this.contact = contact;
        this.imagePath = imagePath;
    }
}
