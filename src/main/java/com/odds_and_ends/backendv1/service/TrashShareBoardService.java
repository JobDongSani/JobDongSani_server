package com.odds_and_ends.backendv1.service;

import com.odds_and_ends.backendv1.dto.trash_share_board.TrashShareBoardDto;
import com.odds_and_ends.backendv1.entity.trash_share_board.TrashShareBoard;
import com.odds_and_ends.backendv1.entity.trash_share_board.TrashShareBoardRepository;
import com.odds_and_ends.backendv1.facade.UserFacade;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TrashShareBoardService {

    private final TrashShareBoardRepository trashShareBoardRepository;
    private final UserFacade userFacade;

    public CommonResponse<Long> save(TrashShareBoardDto trashShareBoardDto){
        TrashShareBoard savedTrashShareBoard = trashShareBoardRepository.save(trashShareBoardDto.toEntity(userFacade.getCurrentUser()));
        return new CommonResponse<>(201, "쓰래ㅁ기 나눔 게시글이 저장되었습니다.", savedTrashShareBoard.getId());
    }
}
