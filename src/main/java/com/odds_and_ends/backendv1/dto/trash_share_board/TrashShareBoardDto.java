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

    private long id;
    private String title;
    private String contents;
    private String contact;
    private String location;
    private String imagePath;

    private String writer;

    public TrashShareBoard toEntity(User user){
        return TrashShareBoard.builder()
                .user(user)
                .title(this.title)
                .contents(this.contents)
                .contact(this.contact)
                .location(this.location)
                .imagePath(this.imagePath)
                .build();
    }

    public static TrashShareBoardDto of(TrashShareBoard trashShareBoard){
        return TrashShareBoardDto.builder()
                .id(trashShareBoard.getId())
                .writer(trashShareBoard.getUser().getName())
                .title(trashShareBoard.getTitle())
                .contents(trashShareBoard.getContents())
                .contact(trashShareBoard.getContact())
                .location(trashShareBoard.getLocation())
                .imagePath(trashShareBoard.getImagePath())
                .build();
    }
}
