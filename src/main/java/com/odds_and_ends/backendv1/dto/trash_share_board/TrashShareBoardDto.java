package com.odds_and_ends.backendv1.dto.trash_share_board;

import com.odds_and_ends.backendv1.entity.trash_share_board.TrashShareBoard;
import com.odds_and_ends.backendv1.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class TrashShareBoardDto {

    private String title;
    private String contents;
    private String contact;
    private String imagePath;

    public TrashShareBoard toEntity(User user){
        return TrashShareBoard.builder()
                .title(this.title)
                .contents(this.contents)
                .contact(this.contact)
                .imagePath(this.imagePath)
                .build();
    }
}
